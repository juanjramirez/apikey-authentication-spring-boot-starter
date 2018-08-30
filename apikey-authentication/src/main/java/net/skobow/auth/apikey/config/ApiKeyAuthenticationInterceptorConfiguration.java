/*
 * MIT License
 *
 * Copyright (c) 2018 Sven Kobow
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package net.skobow.auth.apikey.config;

import net.skobow.auth.apikey.ApiKeyAuthenticationService;
import net.skobow.auth.apikey.webmvc.ApiKeyAuthenticationInterceptor;
import net.skobow.auth.apikey.webmvc.ApiKeyAuthenticationInterceptorProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class ApiKeyAuthenticationInterceptorConfiguration implements WebMvcConfigurer {

    private final ApiKeyAuthenticationService authenticationService;
    private final ApiKeyAuthenticationInterceptorProperties interceptorProperties;

    public ApiKeyAuthenticationInterceptorConfiguration(final ApiKeyAuthenticationService authenticationService,
                                                        final ApiKeyAuthenticationInterceptorProperties interceptorProperties) {
        this.authenticationService = authenticationService;
        this.interceptorProperties = interceptorProperties;
    }

    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(new ApiKeyAuthenticationInterceptor(authenticationService))
                .addPathPatterns(interceptorProperties.getIncludePatterns())
                .excludePathPatterns(interceptorProperties.getExcludePatterns());
    }
}