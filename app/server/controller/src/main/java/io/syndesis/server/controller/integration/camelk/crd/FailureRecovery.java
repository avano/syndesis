/*
 * Copyright (C) 2016 Red Hat, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.syndesis.server.controller.integration.camelk.crd;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;

import javax.annotation.Nullable;

@Value.Immutable
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(builder = FailureRecovery.Builder.class)
// Immutables generates code that fails these checks
@SuppressWarnings({ "ArrayEquals", "ArrayHashCode", "ArrayToString" })
public interface FailureRecovery {
//    type FailureRecovery struct {
//        Attempt     int         `json:"attempt"`
//        AttemptMax  int         `json:"attemptMax"`
//        AttemptTime metav1.Time `json:"attemptTime"`
    @Nullable
    Integer getAttempt();
    @Nullable
    Integer getAttemptMax();
    @Nullable
    String getAttemptTime();

    class Builder extends ImmutableFailureRecovery.Builder {
        // make ImmutableFailureRecovery.Builder which is package private
        // accessible
    }
}
