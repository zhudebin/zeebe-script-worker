/*
 * Copyright © 2017 camunda services GmbH (info@camunda.com)
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
package io.zeebe.script;

import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class ScriptEvaluator {

  private final GraalEvaluator graalEvaluator = new GraalEvaluator();
  private final ScriptEngineEvaluator scriptEngineEvaluator = new ScriptEngineEvaluator();

  public Object evaluate(String language, String script, Map<String, Object> variables) {

    try {

      if (GraalEvaluator.SUPPORTED_LANGUAGES.contains(language)) {
        return graalEvaluator.evaluate(language, script, variables);

      } else {
        return scriptEngineEvaluator.evaluate(language, script, variables);
      }

    } catch (Exception e) {
      final String msg = String.format("Failed to evaluate script '%s' (%s)", script, language);
      throw new RuntimeException(msg, e);
    }
  }
}
