package com.uoft.b07application.ui.login;

public class LoginActivityPresenter {
    LoginActivityModel loginModel;
    LoginActivity loginView;

    public LoginActivityPresenter(LoginActivity view, LoginActivityModel model) {
        this.loginView = view;
        this.loginModel = model;
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
            public void onSuccess(String username, String name, String email, boolean isAdmin) {
                if (isAdmin) {
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

