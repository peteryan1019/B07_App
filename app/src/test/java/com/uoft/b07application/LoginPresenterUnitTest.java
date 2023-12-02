package com.uoft.b07application;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

import android.text.Editable;
import android.view.View;
import android.widget.EditText;

import com.uoft.b07application.ui.login.LoginActivityModel;
import com.uoft.b07application.ui.login.LoginActivity;
import com.uoft.b07application.ui.login.LoginActivityPresenter;
// Import other necessary classes and interfaces used in LoginActivityPresenter and its dependencies

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
}