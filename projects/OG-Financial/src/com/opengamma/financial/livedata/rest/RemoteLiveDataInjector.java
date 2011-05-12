/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.financial.livedata.rest;

import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import com.opengamma.engine.livedata.LiveDataInjector;
import com.opengamma.engine.value.ValueRequirement;
import com.opengamma.id.Identifier;
import com.opengamma.util.ArgumentChecker;
import com.opengamma.util.rest.FudgeRestClient;

/**
 * Provides access to a remote {@link LiveDataInjector}.
 */
public class RemoteLiveDataInjector implements LiveDataInjector {

  private final URI _baseUri;
  private final FudgeRestClient _client;
  
  public RemoteLiveDataInjector(URI baseUri) {
    _baseUri = baseUri;
    _client = FudgeRestClient.create();
  }
  
  @Override
  public void addValue(ValueRequirement valueRequirement, Object value) {
    ArgumentChecker.notNull(valueRequirement, "valueRequirement");
    AddValueRequest request = new AddValueRequest();
    request.setValueRequirement(valueRequirement);
    request.setValue(value);
    addValue(request);
  }
  
  @Override
  public void addValue(Identifier identifier, String valueName, Object value) {
    ArgumentChecker.notNull(identifier, "identifier");
    ArgumentChecker.notNull(valueName, "valueName");
    AddValueRequest request = new AddValueRequest();
    request.setIdentifier(identifier);
    request.setValueName(valueName);
    request.setValue(value);
    addValue(request);
  }
  
  private void addValue(AddValueRequest request) {
    URI uri = UriBuilder.fromUri(_baseUri).path(LiveDataInjectorResource.PATH_ADD).build();
    _client.access(uri).put(request);    
  }

  @Override
  public void removeValue(ValueRequirement valueRequirement) {
    ArgumentChecker.notNull(valueRequirement, "valueRequirement");
    RemoveValueRequest request = new RemoveValueRequest();
    request.setValueRequirement(valueRequirement);
    removeValue(request);
  }

  @Override
  public void removeValue(Identifier identifier, String valueName) {
    ArgumentChecker.notNull(identifier, "identifier");
    ArgumentChecker.notNull(valueName, "valueName");
    RemoveValueRequest request = new RemoveValueRequest();
    request.setIdentifier(identifier);
    request.setValueName(valueName);
    removeValue(request);
  }
  
  private void removeValue(RemoveValueRequest request) {
    URI uri = UriBuilder.fromUri(_baseUri).path(LiveDataInjectorResource.PATH_REMOVE).build();
    _client.access(uri).post(request);    
  }

}
