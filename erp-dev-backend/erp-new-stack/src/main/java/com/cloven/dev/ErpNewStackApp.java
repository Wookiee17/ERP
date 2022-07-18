package com.cloven.dev;

import software.amazon.awscdk.core.App;
import software.amazon.awscdk.core.Environment;
import software.amazon.awscdk.core.StackProps;

import java.util.Arrays;

public class ErpNewStackApp {
    public static void main(final String[] args) {
        App app = new App();

        new ErpNewStackStack(app, "ErpNewStackStack", StackProps.builder()
                .build());

        app.synth();
    }
}
