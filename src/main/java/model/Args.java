package model;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class Args {

    private static final List<String> VALID_SORT_VALUES = Arrays.asList("name", "salary");
    private static final List<String> VALID_ORDER_VALUES = Arrays.asList("asc", "desc");
    private static final List<String> VALID_OUTPUT_VALUES = Arrays.asList("console", "file");

    private String[] originalArgs;

    @Parameter(names = {"--sort", "-s"}, description = "Sort employees by name or salary")
    private String sort;

    @Parameter(names = {"--order"}, description = "Order of sorting: asc or desc")
    private String order;

    @Parameter(names = {"--output", "-o"}, description = "Output destination: console or file")
    private String output;

    @Parameter(names = {"--path"}, description = "Path to output file, should be defined after --output")
    private String path;

    @Parameter(description = "Additional parameters")
    private List<String> additionalParameters;
    public void validate() {
        validateAdditionalParameters();
        validateSortAndOrder();
        validateOutput();
        validatePath();
    }

    private void validateAdditionalParameters() {
        if (additionalParameters != null && !additionalParameters.isEmpty()) {
            throw new ParameterException("Unexpected parameters: " + additionalParameters);
        }
    }

    private void validateSortAndOrder() {
        if (sort != null) {
            if (!VALID_SORT_VALUES.contains(sort.toLowerCase())) {
                throw new IllegalArgumentException("Invalid sorting value. Available values: name, salary.");
            }
            if (order == null) {
                throw new IllegalArgumentException("The --sort parameter cannot be used without --order.");
            }
        }

        if (order != null) {
            if (sort == null) {
                throw new IllegalArgumentException("The --order parameter cannot be used without --sort.");
            }
            if (!VALID_ORDER_VALUES.contains(order.toLowerCase())) {
                throw new IllegalArgumentException("Invalid sorting order. Available values: asc, desc.");
            }
        }
    }

    private void validateOutput() {
        if (output != null && !VALID_OUTPUT_VALUES.contains(output.toLowerCase())) {
            throw new IllegalArgumentException("Invalid --output value. Available values: console, file.");
        }
    }

    private void validatePath() {
        if ("file".equalsIgnoreCase(output)) {
            if (path == null || path.isEmpty()) {
                throw new IllegalArgumentException("--output=file is specified, but --path is not specified.");
            }
            checkPathOrder();
        } else if (path != null) {
            throw new IllegalArgumentException("The --path is specified without --output=file.");
        }
    }

    private void checkPathOrder() {
        for (int i = 0; i < originalArgs.length - 1; i++) {
            if ((originalArgs[i].startsWith("--output") || originalArgs[i].startsWith("-o"))) {
                if (i + 2 < originalArgs.length && originalArgs[i + 2].startsWith("--path")) {
                    return;
                }
            }
        }
        throw new IllegalArgumentException("--path should follow immediately after --output=file.");
    }
}