package vn.touchspace.example.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by GNUD on 16/04/2018.
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerService {
}
