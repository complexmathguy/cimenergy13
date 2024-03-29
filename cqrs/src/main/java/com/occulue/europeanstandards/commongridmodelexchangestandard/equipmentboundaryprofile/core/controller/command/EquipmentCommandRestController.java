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
package com.occulue.europeanstandards.commongridmodelexchangestandard.equipmentboundaryprofile.core.controller.command;

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
 * Implements Spring Controller command CQRS processing for entity Equipment.
 *
 * @author your_name_here
 */
@CrossOrigin
@RestController
@RequestMapping("/Equipment")
public class EquipmentCommandRestController extends BaseSpringRestController {

  /**
   * Handles create a Equipment. if not key provided, calls create, otherwise calls save
   *
   * @param Equipment equipment
   * @return CompletableFuture<UUID>
   */
  @PostMapping("/create")
  public CompletableFuture<UUID> create(
      @RequestBody(required = true) CreateEquipmentCommand command) {
    CompletableFuture<UUID> completableFuture = null;
    try {

      completableFuture = EquipmentBusinessDelegate.getEquipmentInstance().createEquipment(command);
    } catch (Throwable exc) {
      LOGGER.log(Level.WARNING, exc.getMessage(), exc);
    }

    return completableFuture;
  }

  /**
   * Handles updating a Equipment. if no key provided, calls create, otherwise calls save
   *
   * @param Equipment equipment
   * @return CompletableFuture<Void>
   */
  @PutMapping("/update")
  public CompletableFuture<Void> update(
      @RequestBody(required = true) UpdateEquipmentCommand command) {
    CompletableFuture<Void> completableFuture = null;
    try {
      // -----------------------------------------------
      // delegate the UpdateEquipmentCommand
      // -----------------------------------------------
      completableFuture = EquipmentBusinessDelegate.getEquipmentInstance().updateEquipment(command);
      ;
    } catch (Throwable exc) {
      LOGGER.log(
          Level.WARNING,
          "EquipmentController:update() - successfully update Equipment - " + exc.getMessage());
    }

    return completableFuture;
  }

  /**
   * Handles deleting a Equipment entity
   *
   * @param command ${class.getDeleteCommandAlias()}
   * @return CompletableFuture<Void>
   */
  @DeleteMapping("/delete")
  public CompletableFuture<Void> delete(@RequestParam(required = true) UUID equipmentId) {
    CompletableFuture<Void> completableFuture = null;
    DeleteEquipmentCommand command = new DeleteEquipmentCommand(equipmentId);

    try {
      EquipmentBusinessDelegate delegate = EquipmentBusinessDelegate.getEquipmentInstance();

      completableFuture = delegate.delete(command);
      LOGGER.log(
          Level.WARNING, "Successfully deleted Equipment with key " + command.getEquipmentId());
    } catch (Throwable exc) {
      LOGGER.log(Level.WARNING, exc.getMessage());
    }

    return completableFuture;
  }

  /**
   * save Equipments on Equipment
   *
   * @param command AssignEquipmentsToEquipmentCommand
   */
  @PutMapping("/addToEquipments")
  public void addToEquipments(
      @RequestBody(required = true) AssignEquipmentsToEquipmentCommand command) {
    try {
      EquipmentBusinessDelegate.getEquipmentInstance().addToEquipments(command);
    } catch (Exception exc) {
      LOGGER.log(Level.WARNING, "Failed to add to Set Equipments", exc);
    }
  }

  /**
   * remove Equipments on Equipment
   *
   * @param command RemoveEquipmentsFromEquipmentCommand
   */
  @PutMapping("/removeFromEquipments")
  public void removeFromEquipments(
      @RequestBody(required = true) RemoveEquipmentsFromEquipmentCommand command) {
    try {
      EquipmentBusinessDelegate.getEquipmentInstance().removeFromEquipments(command);
    } catch (Exception exc) {
      LOGGER.log(Level.WARNING, "Failed to remove from Set Equipments", exc);
    }
  }

  // ************************************************************************
  // Attributes
  // ************************************************************************
  protected Equipment equipment = null;
  private static final Logger LOGGER =
      Logger.getLogger(EquipmentCommandRestController.class.getName());
}
