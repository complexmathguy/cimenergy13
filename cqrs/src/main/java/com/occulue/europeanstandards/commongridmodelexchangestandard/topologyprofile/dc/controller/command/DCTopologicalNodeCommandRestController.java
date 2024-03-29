/**
 * ***************************************************************************** Turnstone Biologics
 * Confidential
 *
 * <p>2018 Turnstone Biologics All Rights Reserved.
 *
 * <p>This file is subject to the terms and conditions defined in file 'license.txt', which is part
 * of this source code package.
 *
 * <p>Contributors : Turnstone Biologics - General Release
 * ****************************************************************************
 */
package com.occulue.europeanstandards.commongridmodelexchangestandard.topologyprofile.dc.controller.command;

import com.occulue.api.*;
import com.occulue.command.*;
import com.occulue.controller.*;
import com.occulue.delegate.*;
import com.occulue.entity.*;
import com.occulue.exception.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.web.bind.annotation.*;

/**
 * Implements Spring Controller command CQRS processing for entity DCTopologicalNode.
 *
 * @author your_name_here
 */
@CrossOrigin
@RestController
@RequestMapping("/DCTopologicalNode")
public class DCTopologicalNodeCommandRestController extends BaseSpringRestController {

  /**
   * Handles create a DCTopologicalNode. if not key provided, calls create, otherwise calls save
   *
   * @param DCTopologicalNode dCTopologicalNode
   * @return CompletableFuture<UUID>
   */
  @PostMapping("/create")
  public CompletableFuture<UUID> create(
      @RequestBody(required = true) CreateDCTopologicalNodeCommand command) {
    CompletableFuture<UUID> completableFuture = null;
    try {

      completableFuture =
          DCTopologicalNodeBusinessDelegate.getDCTopologicalNodeInstance()
              .createDCTopologicalNode(command);
    } catch (Throwable exc) {
      LOGGER.log(Level.WARNING, exc.getMessage(), exc);
    }

    return completableFuture;
  }

  /**
   * Handles updating a DCTopologicalNode. if no key provided, calls create, otherwise calls save
   *
   * @param DCTopologicalNode dCTopologicalNode
   * @return CompletableFuture<Void>
   */
  @PutMapping("/update")
  public CompletableFuture<Void> update(
      @RequestBody(required = true) UpdateDCTopologicalNodeCommand command) {
    CompletableFuture<Void> completableFuture = null;
    try {
      // -----------------------------------------------
      // delegate the UpdateDCTopologicalNodeCommand
      // -----------------------------------------------
      completableFuture =
          DCTopologicalNodeBusinessDelegate.getDCTopologicalNodeInstance()
              .updateDCTopologicalNode(command);
      ;
    } catch (Throwable exc) {
      LOGGER.log(
          Level.WARNING,
          "DCTopologicalNodeController:update() - successfully update DCTopologicalNode - "
              + exc.getMessage());
    }

    return completableFuture;
  }

  /**
   * Handles deleting a DCTopologicalNode entity
   *
   * @param command ${class.getDeleteCommandAlias()}
   * @return CompletableFuture<Void>
   */
  @DeleteMapping("/delete")
  public CompletableFuture<Void> delete(@RequestParam(required = true) UUID dCTopologicalNodeId) {
    CompletableFuture<Void> completableFuture = null;
    DeleteDCTopologicalNodeCommand command =
        new DeleteDCTopologicalNodeCommand(dCTopologicalNodeId);

    try {
      DCTopologicalNodeBusinessDelegate delegate =
          DCTopologicalNodeBusinessDelegate.getDCTopologicalNodeInstance();

      completableFuture = delegate.delete(command);
      LOGGER.log(
          Level.WARNING,
          "Successfully deleted DCTopologicalNode with key " + command.getDCTopologicalNodeId());
    } catch (Throwable exc) {
      LOGGER.log(Level.WARNING, exc.getMessage());
    }

    return completableFuture;
  }

  /**
   * save DCTopologicalNode on DCTopologicalNode
   *
   * @param command AssignDCTopologicalNodeToDCTopologicalNodeCommand
   */
  @PutMapping("/addToDCTopologicalNode")
  public void addToDCTopologicalNode(
      @RequestBody(required = true) AssignDCTopologicalNodeToDCTopologicalNodeCommand command) {
    try {
      DCTopologicalNodeBusinessDelegate.getDCTopologicalNodeInstance()
          .addToDCTopologicalNode(command);
    } catch (Exception exc) {
      LOGGER.log(Level.WARNING, "Failed to add to Set DCTopologicalNode", exc);
    }
  }

  /**
   * remove DCTopologicalNode on DCTopologicalNode
   *
   * @param command RemoveDCTopologicalNodeFromDCTopologicalNodeCommand
   */
  @PutMapping("/removeFromDCTopologicalNode")
  public void removeFromDCTopologicalNode(
      @RequestBody(required = true) RemoveDCTopologicalNodeFromDCTopologicalNodeCommand command) {
    try {
      DCTopologicalNodeBusinessDelegate.getDCTopologicalNodeInstance()
          .removeFromDCTopologicalNode(command);
    } catch (Exception exc) {
      LOGGER.log(Level.WARNING, "Failed to remove from Set DCTopologicalNode", exc);
    }
  }

  // ************************************************************************
  // Attributes
  // ************************************************************************
  protected DCTopologicalNode dCTopologicalNode = null;
  private static final Logger LOGGER =
      Logger.getLogger(DCTopologicalNodeCommandRestController.class.getName());
}
