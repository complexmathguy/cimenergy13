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
package com.occulue.europeanstandards.commongridmodelexchangestandard.equipmentprofile.controlarea.delegate;

import com.occulue.api.*;
import com.occulue.delegate.*;
import com.occulue.entity.*;
import com.occulue.europeanstandards.commongridmodelexchangestandard.equipmentprofile.controlarea.validator.*;
import com.occulue.exception.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.queryhandling.QueryUpdateEmitter;

/**
 * ControlAreaGeneratingUnit business delegate class.
 *
 * <p>This class implements the Business Delegate design pattern for the purpose of:
 *
 * <ol>
 *   <li>Reducing coupling between the business tier and a client of the business tier by hiding all
 *       business-tier implementation details
 *   <li>Improving the available of ControlAreaGeneratingUnit related services in the case of a
 *       ControlAreaGeneratingUnit business related service failing.
 *   <li>Exposes a simpler, uniform ControlAreaGeneratingUnit interface to the business tier, making
 *       it easy for clients to consume a simple Java object.
 *   <li>Hides the communication protocol that may be required to fulfill ControlAreaGeneratingUnit
 *       business related services.
 * </ol>
 *
 * <p>
 *
 * @author your_name_here
 */
public class ControlAreaGeneratingUnitBusinessDelegate extends BaseBusinessDelegate {
  // ************************************************************************
  // Public Methods
  // ************************************************************************
  /** Default Constructor */
  public ControlAreaGeneratingUnitBusinessDelegate() {
    queryGateway = applicationContext.getBean(QueryGateway.class);
    commandGateway = applicationContext.getBean(CommandGateway.class);
    queryUpdateEmitter = applicationContext.getBean(QueryUpdateEmitter.class);
  }

  /**
   * ControlAreaGeneratingUnit Business Delegate Factory Method
   *
   * <p>All methods are expected to be self-sufficient.
   *
   * @return ControlAreaGeneratingUnitBusinessDelegate
   */
  public static ControlAreaGeneratingUnitBusinessDelegate getControlAreaGeneratingUnitInstance() {
    return (new ControlAreaGeneratingUnitBusinessDelegate());
  }

  /**
   * Creates the provided command.
   *
   * @param command ${class.getCreateCommandAlias()}
   * @exception ProcessingException
   * @exception IllegalArgumentException
   * @return CompletableFuture<UUID>
   */
  public CompletableFuture<UUID> createControlAreaGeneratingUnit(
      CreateControlAreaGeneratingUnitCommand command)
      throws ProcessingException, IllegalArgumentException {

    CompletableFuture<UUID> completableFuture = null;

    try {
      // --------------------------------------
      // assign identity now if none
      // --------------------------------------
      if (command.getControlAreaGeneratingUnitId() == null)
        command.setControlAreaGeneratingUnitId(UUID.randomUUID());

      // --------------------------------------
      // validate the command
      // --------------------------------------
      ControlAreaGeneratingUnitValidator.getInstance().validate(command);

      // ---------------------------------------
      // issue the CreateControlAreaGeneratingUnitCommand - by convention the future return value
      // for a create command
      // that is handled by the constructor of an aggregate will return the UUID
      // ---------------------------------------
      completableFuture = commandGateway.send(command);

      LOGGER.log(
          Level.INFO,
          "return from Command Gateway for CreateControlAreaGeneratingUnitCommand of ControlAreaGeneratingUnit is "
              + command);

    } catch (Exception exc) {
      final String errMsg = "Unable to create ControlAreaGeneratingUnit - " + exc;
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    } finally {
    }

    return completableFuture;
  }

  /**
   * Update the provided command.
   *
   * @param command UpdateControlAreaGeneratingUnitCommand
   * @return CompletableFuture<Void>
   * @exception ProcessingException
   * @exception IllegalArgumentException
   */
  public CompletableFuture<Void> updateControlAreaGeneratingUnit(
      UpdateControlAreaGeneratingUnitCommand command)
      throws ProcessingException, IllegalArgumentException {
    CompletableFuture<Void> completableFuture = null;

    try {

      // --------------------------------------
      // validate
      // --------------------------------------
      ControlAreaGeneratingUnitValidator.getInstance().validate(command);

      // --------------------------------------
      // issue the UpdateControlAreaGeneratingUnitCommand and return right away
      // --------------------------------------
      completableFuture = commandGateway.send(command);
    } catch (Exception exc) {
      final String errMsg = "Unable to save ControlAreaGeneratingUnit - " + exc;
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    }

    return completableFuture;
  }

  /**
   * Deletes the associatied value object
   *
   * @param command DeleteControlAreaGeneratingUnitCommand
   * @return CompletableFuture<Void>
   * @exception ProcessingException
   */
  public CompletableFuture<Void> delete(DeleteControlAreaGeneratingUnitCommand command)
      throws ProcessingException, IllegalArgumentException {

    CompletableFuture<Void> completableFuture = null;

    try {
      // --------------------------------------
      // validate the command
      // --------------------------------------
      ControlAreaGeneratingUnitValidator.getInstance().validate(command);

      // --------------------------------------
      // issue the DeleteControlAreaGeneratingUnitCommand and return right away
      // --------------------------------------
      completableFuture = commandGateway.send(command);
    } catch (Exception exc) {
      final String errMsg =
          "Unable to delete ControlAreaGeneratingUnit using Id = "
              + command.getControlAreaGeneratingUnitId();
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    } finally {
    }

    return completableFuture;
  }

  /**
   * Method to retrieve the ControlAreaGeneratingUnit via ControlAreaGeneratingUnitFetchOneSummary
   *
   * @param summary ControlAreaGeneratingUnitFetchOneSummary
   * @return ControlAreaGeneratingUnitFetchOneResponse
   * @exception ProcessingException - Thrown if processing any related problems
   * @exception IllegalArgumentException
   */
  public ControlAreaGeneratingUnit getControlAreaGeneratingUnit(
      ControlAreaGeneratingUnitFetchOneSummary summary)
      throws ProcessingException, IllegalArgumentException {

    if (summary == null)
      throw new IllegalArgumentException(
          "ControlAreaGeneratingUnitFetchOneSummary arg cannot be null");

    ControlAreaGeneratingUnit entity = null;

    try {
      // --------------------------------------
      // validate the fetch one summary
      // --------------------------------------
      ControlAreaGeneratingUnitValidator.getInstance().validate(summary);

      // --------------------------------------
      // use queryGateway to send request to Find a ControlAreaGeneratingUnit
      // --------------------------------------
      CompletableFuture<ControlAreaGeneratingUnit> futureEntity =
          queryGateway.query(
              new FindControlAreaGeneratingUnitQuery(
                  new LoadControlAreaGeneratingUnitFilter(
                      summary.getControlAreaGeneratingUnitId())),
              ResponseTypes.instanceOf(ControlAreaGeneratingUnit.class));

      entity = futureEntity.get();
    } catch (Exception exc) {
      final String errMsg =
          "Unable to locate ControlAreaGeneratingUnit with id "
              + summary.getControlAreaGeneratingUnitId();
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    } finally {
    }

    return entity;
  }

  /**
   * Method to retrieve a collection of all ControlAreaGeneratingUnits
   *
   * @return List<ControlAreaGeneratingUnit>
   * @exception ProcessingException Thrown if any problems
   */
  public List<ControlAreaGeneratingUnit> getAllControlAreaGeneratingUnit()
      throws ProcessingException {
    List<ControlAreaGeneratingUnit> list = null;

    try {
      CompletableFuture<List<ControlAreaGeneratingUnit>> futureList =
          queryGateway.query(
              new FindAllControlAreaGeneratingUnitQuery(),
              ResponseTypes.multipleInstancesOf(ControlAreaGeneratingUnit.class));

      list = futureList.get();
    } catch (Exception exc) {
      String errMsg = "Failed to get all ControlAreaGeneratingUnit";
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    } finally {
    }

    return list;
  }

  /**
   * add ControlAreaGeneratingUnit to ControlAreaGeneratingUnit
   *
   * @param command AssignControlAreaGeneratingUnitToControlAreaGeneratingUnitCommand
   * @exception ProcessingException
   */
  public void addToControlAreaGeneratingUnit(
      AssignControlAreaGeneratingUnitToControlAreaGeneratingUnitCommand command)
      throws ProcessingException {

    // -------------------------------------------
    // load the parent
    // -------------------------------------------
    load(command.getControlAreaGeneratingUnitId());

    com.occulue.europeanstandards.commongridmodelexchangestandard.equipmentprofile.controlarea
            .delegate.ControlAreaGeneratingUnitBusinessDelegate
        childDelegate =
            ControlAreaGeneratingUnitBusinessDelegate.getControlAreaGeneratingUnitInstance();
    ControlAreaGeneratingUnitBusinessDelegate parentDelegate =
        ControlAreaGeneratingUnitBusinessDelegate.getControlAreaGeneratingUnitInstance();
    UUID childId = command.getAddTo().getControlAreaGeneratingUnitId();

    try {
      // --------------------------------------
      // validate the command
      // --------------------------------------
      ControlAreaGeneratingUnitValidator.getInstance().validate(command);

      // --------------------------------------
      // issue the command
      // --------------------------------------
      commandGateway.sendAndWait(command);
    } catch (Exception exc) {
      final String msg =
          "Failed to add a ControlAreaGeneratingUnit as ControlAreaGeneratingUnit to ControlAreaGeneratingUnit";
      LOGGER.log(Level.WARNING, msg, exc);
      throw new ProcessingException(msg, exc);
    }
  }

  /**
   * remove ControlAreaGeneratingUnit from ControlAreaGeneratingUnit
   *
   * @param command RemoveControlAreaGeneratingUnitFromControlAreaGeneratingUnitCommand
   * @exception ProcessingException
   */
  public void removeFromControlAreaGeneratingUnit(
      RemoveControlAreaGeneratingUnitFromControlAreaGeneratingUnitCommand command)
      throws ProcessingException {

    com.occulue.europeanstandards.commongridmodelexchangestandard.equipmentprofile.controlarea
            .delegate.ControlAreaGeneratingUnitBusinessDelegate
        childDelegate =
            ControlAreaGeneratingUnitBusinessDelegate.getControlAreaGeneratingUnitInstance();
    UUID childId = command.getRemoveFrom().getControlAreaGeneratingUnitId();

    try {

      // --------------------------------------
      // validate the command
      // --------------------------------------
      ControlAreaGeneratingUnitValidator.getInstance().validate(command);

      // --------------------------------------
      // issue the command
      // --------------------------------------
      commandGateway.sendAndWait(command);

    } catch (Exception exc) {
      final String msg = "Failed to remove child using Id " + childId;
      LOGGER.log(Level.WARNING, msg, exc);
      throw new ProcessingException(msg, exc);
    }
  }

  /**
   * Internal helper method to load the root
   *
   * @param id UUID
   * @return ControlAreaGeneratingUnit
   */
  private ControlAreaGeneratingUnit load(UUID id) throws ProcessingException {
    controlAreaGeneratingUnit =
        ControlAreaGeneratingUnitBusinessDelegate.getControlAreaGeneratingUnitInstance()
            .getControlAreaGeneratingUnit(new ControlAreaGeneratingUnitFetchOneSummary(id));
    return controlAreaGeneratingUnit;
  }

  // ************************************************************************
  // Attributes
  // ************************************************************************
  private final QueryGateway queryGateway;
  private final CommandGateway commandGateway;
  private final QueryUpdateEmitter queryUpdateEmitter;
  private ControlAreaGeneratingUnit controlAreaGeneratingUnit = null;
  private static final Logger LOGGER =
      Logger.getLogger(ControlAreaGeneratingUnitBusinessDelegate.class.getName());
}
