package com.uoft.b07application;

import static org.mockito.Mockito.*;

import com.uoft.b07application.ui.login.LoginActivityModel;
import com.uoft.b07application.ui.login.LoginActivity;
import com.uoft.b07application.ui.login.LoginActivityPresenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class LoginPresenterUnitTest {
    @Mock
    LoginActivityModel mockLoginModel;

    @Mock
    LoginActivity mockLoginView;

    LoginActivityPresenter presenter;

    @Before
    public void setUp() {
        presenter = new LoginActivityPresenter(mockLoginView, mockLoginModel);
    }

    @Test
    public void testEmptyUsername() {
        // When username is empty, setUsernameError should be called
        presenter.validateCredentials("", "password");
        verify(mockLoginView).setUsernameError("Username cannot be empty");
    }

    @Test
    public void testEmptyPassword() {
        // When password is empty, setPasswordError should be called
        presenter.validateCredentials("username", "");
        verify(mockLoginView).setPasswordError("Password cannot be empty");
    }

    @Test
    public void testAdminLogin() {
        String adminUsername = "admin";
        String adminPassword = "admin_password";

        doAnswer(invocation -> {
            LoginActivityModel.OnLoginFinishedListener listener = invocation.getArgument(2);
            listener.onSuccess(adminUsername, "Admin Name", "admin@example.com", true);
            return null;
        }).when(mockLoginModel).loginUser(eq(adminUsername), eq(adminPassword), any(LoginActivityModel.OnLoginFinishedListener.class));

        presenter.validateCredentials(adminUsername, adminPassword);

        // Verify interactions: Expect the view to navigate to the admin screen
        verify(mockLoginView).navigateToAdminScreen(eq(adminUsername), anyString(), anyString());
        verifyNoMoreInteractions(mockLoginView);
    }

    @Test
    public void testStudentLogin() {
        String studentUsername = "student";
        String studentPassword = "student_password";

        doAnswer(invocation -> {
            LoginActivityModel.OnLoginFinishedListener listener = invocation.getArgument(2);
            listener.onSuccess(studentUsername, "Student Name", "student@example.com", false);
            return null;
        }).when(mockLoginModel).loginUser(eq(studentUsername), eq(studentPassword), any(LoginActivityModel.OnLoginFinishedListener.class));

        presenter.validateCredentials(studentUsername, studentPassword);

        // Verify interactions: Expect the view to navigate to the student screen
        verify(mockLoginView).navigateToStudentScreen(eq(studentUsername), anyString(), anyString());
        verifyNoMoreInteractions(mockLoginView);
    }

    @Test
    public void testLoginFail() {
        String username = "invalid_username";
        String password = "invalid_password";

        doAnswer(invocation -> {
            LoginActivityModel.OnLoginFinishedListener listener = invocation.getArgument(2);
            listener.onError("Invalid Credentials");
            return null;
        }).when(mockLoginModel).loginUser(eq(username), eq(password), any(LoginActivityModel.OnLoginFinishedListener.class));

        presenter.validateCredentials(username, password);

        // Verify interactions: Expect the view to display a login error
        verify(mockLoginView).showLoginError("Invalid Credentials");
        verifyNoMoreInteractions(mockLoginView);
    }
}