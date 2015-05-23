package it.androidavanzato.jvmtest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GcmHelper3Test {

    private GcmHelper3 gcmHelper;

    @Mock private PreferencesWrapper preferencesWrapper;

    @Mock private GoogleCloudMessagingWrapper googleCloudMessagingWrapper;

    @Before
    public void init() {
        gcmHelper = new GcmHelper3(preferencesWrapper, googleCloudMessagingWrapper);
    }

    @Test
    public void testFirstRegistration() throws IOException {
        when(preferencesWrapper.getString(anyString(), anyString())).thenReturn(null);
        when(googleCloudMessagingWrapper.register(anyString())).thenReturn("123");
        gcmHelper.registerToGcm();
        verify(preferencesWrapper).putString(anyString(), Matchers.eq("123"));
    }

    @Test
    public void testAlreadyRegistered() throws IOException {
        when(preferencesWrapper.getString(anyString(), anyString())).thenReturn("123");
        gcmHelper.registerToGcm();
        verify(preferencesWrapper, never()).putString(anyString(), anyString());
        verify(googleCloudMessagingWrapper, never()).register(anyString());
    }
}
