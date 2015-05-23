package it.androidavanzato.jvmtest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GcmHelper4Test {

    private GcmHelper4 gcmHelper;

    @Mock private StringPreference stringPreference;

    @Mock private GoogleCloudMessagingWrapper googleCloudMessagingWrapper;

    @Before
    public void init() {
        gcmHelper = new GcmHelper4(stringPreference, googleCloudMessagingWrapper);
    }

    @Test
    public void testFirstRegistration() throws IOException {
        when(stringPreference.get()).thenReturn(null);
        when(googleCloudMessagingWrapper.register(anyString())).thenReturn("123");
        gcmHelper.registerToGcm();
        verify(stringPreference).put(eq("123"));
    }

    @Test
    public void testAlreadyRegistered() throws IOException {
        when(stringPreference.get()).thenReturn("123");
        gcmHelper.registerToGcm();
        verify(stringPreference, never()).put(anyString());
        verify(googleCloudMessagingWrapper, never()).register(anyString());
    }
}
