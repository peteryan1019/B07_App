package com.uoft.b07application.ui.login;

public class SignupActivityPresenter {
    private final SignupActivityModel signupModel;
    private final SignupActivity signupView;

    public SignupActivityPresenter(SignupActivity view, SignupActivityModel model) {
        this.signupView = view;
        this.signupModel = model;
    }

    public void validateSignup(String name, String email, String username, String password, boolean isAdmin) {
        if (name.isEmpty() || email.isEmpty() || username.isEmpty() || password.isEmpty()) {
            signupView.showSignupError("Please fill in all fields!");
            return;
        }

        if (!name.matches("^[a-zA-Z ]+$")) {
            signupView.showSignupError("Name should contain only alphabetical characters and spaces!");
            return;
        }

        if (!username.matches("^[a-z0-9]+$")) {
            signupView.showSignupError("User name cannot contain spaces and must be lowercase alphanumerical!");
            return;
        }

        if (email.contains(" ")) {
            signupView.showSignupError("Invalid email!");
            return;
        }

        if (password.contains(" ")) {
            signupView.showSignupError("Password cannot contain spaces!");
            return;
        }

        // Call the Model to validate signup and register the user
        signupModel.registerUser(name, email, username, password, isAdmin, new SignupActivityModel.OnSignupFinishedListener() {
            @Override
            public void onSuccess() {
                signupView.showSignupSuccess();
            }

            @Override
            public void onError(String errorMessage) {
                signupView.showSignupError(errorMessage);
            }
        });
    }
}


