package com.uoft.b07application;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

import android.text.Editable;
import android.view.View;
import android.widget.EditText;

import com.uoft.b07application.ui.login.LoginActivityModel;
import com.uoft.b07application.ui.login.LoginActivity;
import com.uoft.b07application.ui.login.LoginActivityPresenter;

@RunWith(MockitoJUnitRunner.class)
public class LoginPresenterUnitTest {
    @Mock
    LoginActivityModel mockLoginModel;

    @Mock
    LoginActivity mockLoginView;

    @Mock
    EditText mockEditText;

    @Mock
    Editable mockEdit;

    @Mock
    View baseView;

    @Test
    public void testEmptyUsername() {
        // When username is empty, setUsernameError should be called
        LoginActivityPresenter presenter = new LoginActivityPresenter(mockLoginView, mockLoginModel);
        presenter.validateCredentials("", "password");
        verify(mockLoginView).setUsernameError("Username cannot be empty");
    }

    @Test
    public void testEmptyPassword() {
        // When password is empty, setPasswordError should be called
        LoginActivityPresenter presenter = new LoginActivityPresenter(mockLoginView, mockLoginModel);
        presenter.validateCredentials("username", "");
        verify(mockLoginView).setPasswordError("Password cannot be empty");
    }

    @Test
    public void testAdminLogin() {
        String adminUsername = "admin";
        String adminPassword = "admin_password";

        // Mock a successful login callback for an admin user
        doAnswer(invocation -> {
            LoginActivityModel.OnLoginFinishedListener listener = invocation.getArgument(2);
            listener.onSuccess(adminUsername, "Admin Name", "admin@example.com", true);
            return null;
        }).when(mockLoginModel).loginUser(eq(adminUsername), eq(adminPassword), any(LoginActivityModel.OnLoginFinishedListener.class));

        // Trigger validateCredentials
        LoginActivityPresenter presenter = new LoginActivityPresenter(mockLoginView, mockLoginModel);
        presenter.validateCredentials(adminUsername, adminPassword);

        // Verify interactions: Expect the view to navigate to the admin screen
        verify(mockLoginView).navigateToAdminScreen(eq(adminUsername), anyString(), anyString());
        verifyNoMoreInteractions(mockLoginView);
    }

    @Test
    public void testStudentLogin() {
        String studentUsername = "student";
        String studentPassword = "student_password";

        // Mock a successful login callback for a student user
        doAnswer(invocation -> {
            LoginActivityModel.OnLoginFinishedListener listener = invocation.getArgument(2);
            listener.onSuccess(studentUsername, "Student Name", "student@example.com", false);
            return null;
        }).when(mockLoginModel).loginUser(eq(studentUsername), eq(studentPassword), any(LoginActivityModel.OnLoginFinishedListener.class));

        // Trigger validateCredentials
        LoginActivityPresenter presenter = new LoginActivityPresenter(mockLoginView, mockLoginModel);
        presenter.validateCredentials(studentUsername, studentPassword);

        // Verify interactions: Expect the view to navigate to the student screen
        verify(mockLoginView).navigateToStudentScreen(eq(studentUsername), anyString(), anyString());
        verifyNoMoreInteractions(mockLoginView);
    }
}