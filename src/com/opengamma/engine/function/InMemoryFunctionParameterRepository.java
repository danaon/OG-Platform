/**
 * Copyright (C) 2009 - 2009 by OpenGamma Inc.
 *
 * Please see distribution for license.
 */
package com.opengamma.engine.function;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.opengamma.util.ArgumentChecker;

/**
 * An in-memory implementation of {@link FunctionRepository}.
 * This can either be used as-is through a factory which scans available functions,
 * or it can be used as a cache on top of a more costly function repository. 
 *
 */
public class InMemoryFunctionParameterRepository implements FunctionParameterRepository {
  private final Set<ParameterizedFunction> _functions = new HashSet<ParameterizedFunction>();
  
  public InMemoryFunctionParameterRepository() {
  }
  
  public InMemoryFunctionParameterRepository(FunctionRepository repository) {
    addAllFunctions(repository);
  }
  
  public void addFunction(ParameterizedFunction function) {
    ArgumentChecker.notNull(function, "Function");
    _functions.add(function);
    if (function.getUniqueId() == null) {
      function.setUniqueId(Integer.toString(_functions.size()));
    }
  }
  
  public void addAllFunctions(FunctionRepository repository) {
    for (FunctionDefinition function : repository.getAllFunctions()) {
      ParameterizedFunction parameterizedFunction = new ParameterizedFunction(
          function,
          function.getDefaultParameters());
      addFunction(parameterizedFunction);
    }
  }

  @Override
  public Collection<ParameterizedFunction> getAllFunctionParameters() {
    return Collections.unmodifiableCollection(_functions);
  }

}
