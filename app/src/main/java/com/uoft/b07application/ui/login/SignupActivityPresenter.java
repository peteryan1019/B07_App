package com.uoft.b07application.ui.login;

public class SignupActivityPresenter {
    private final SignupActivityModel signupModel;
    private final SignupActivity signupView;

    public SignupActivityPresenter(SignupActivity view) {
        this.signupView = view;
        signupModel = new SignupActivityModel();
    }

    public void validateSignup(String name, String email, String username, String password, boolean isAdmin) {
        if (name.isEmpty() || email.isEmpty() || username.isEmpty() || password.isEmpty()) {
            signupView.showFieldError("Please fill in all fields!");
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


