package com.uoft.b07application.ui.login;

public class LoginActivityPresenter {
    private final LoginActivityModel loginModel;
    private final LoginActivity loginView;

    public LoginActivityPresenter(LoginActivity view) {
        this.loginView = view;
        loginModel = new LoginActivityModel();
    }

    public void validateCredentials(String username, String password) {
        if (username.isEmpty()) {
            loginView.setUsernameError("Username cannot be empty");
            return;
        }

        if (password.isEmpty()) {
            loginView.setPasswordError("Password cannot be empty");
            return;
        }

        // Call the Model to validate the user's credentials
        loginModel.loginUser(username, password, new LoginActivityModel.OnLoginFinishedListener() {
            @Override
            public void onSuccess(String username, String name, String email, boolean isAdminOrStudent) {
                if (isAdminOrStudent) {
                    loginView.navigateToAdminScreen(username, name, email);
                } else {
                    loginView.navigateToStudentScreen(username, name, email);
                }
            }

            @Override
            public void onError(String errorMessage) {
                loginView.showLoginError(errorMessage);
            }
        });
    }
}

