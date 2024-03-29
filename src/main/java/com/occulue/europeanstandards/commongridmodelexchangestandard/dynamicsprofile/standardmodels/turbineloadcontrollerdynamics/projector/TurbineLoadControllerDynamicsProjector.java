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
package com.occulue.europeanstandards.commongridmodelexchangestandard.dynamicsprofile.standardmodels.turbineloadcontrollerdynamics.projector;

import com.occulue.api.*;
import com.occulue.entity.*;
import com.occulue.europeanstandards.commongridmodelexchangestandard.dynamicsprofile.standardmodels.turbineloadcontrollerdynamics.repository.*;
import com.occulue.exception.*;
import java.util.*;
import java.util.logging.Logger;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.axonframework.queryhandling.QueryUpdateEmitter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Projector for TurbineLoadControllerDynamics as outlined for the CQRS pattern. All event handling
 * and query handling related to TurbineLoadControllerDynamics are invoked here and dispersed as an
 * event to be handled elsewhere.
 *
 * <p>Commands are handled by TurbineLoadControllerDynamicsAggregate
 *
 * @author your_name_here
 */
// @ProcessingGroup("turbineLoadControllerDynamics")
@Component("turbineLoadControllerDynamics-projector")
public class TurbineLoadControllerDynamicsProjector
    extends TurbineLoadControllerDynamicsEntityProjector {

  // core constructor
  public TurbineLoadControllerDynamicsProjector(
      TurbineLoadControllerDynamicsRepository repository, QueryUpdateEmitter queryUpdateEmitter) {
    super(repository);
    this.queryUpdateEmitter = queryUpdateEmitter;
  }

  /*
   * @param	event CreateTurbineLoadControllerDynamicsEvent
   */
  @EventHandler(payloadType = CreateTurbineLoadControllerDynamicsEvent.class)
  public void handle(CreateTurbineLoadControllerDynamicsEvent event) {
    LOGGER.info("handling CreateTurbineLoadControllerDynamicsEvent - " + event);
    TurbineLoadControllerDynamics entity = new TurbineLoadControllerDynamics();
    entity.setTurbineLoadControllerDynamicsId(event.getTurbineLoadControllerDynamicsId());

    // ------------------------------------------
    // persist a new one
    // ------------------------------------------
    create(entity);

    // ------------------------------------------
    // emit to subscribers that find all
    // ------------------------------------------
    emitFindAllTurbineLoadControllerDynamics(entity);
  }

  /*
   * @param	event UpdateTurbineLoadControllerDynamicsEvent
   */
  @EventHandler(payloadType = UpdateTurbineLoadControllerDynamicsEvent.class)
  public void handle(UpdateTurbineLoadControllerDynamicsEvent event) {
    LOGGER.info("handling UpdateTurbineLoadControllerDynamicsEvent - " + event);

    TurbineLoadControllerDynamics entity = new TurbineLoadControllerDynamics();
    entity.setTurbineLoadControllerDynamicsId(event.getTurbineLoadControllerDynamicsId());
    entity.setTurbineLoadControllerDynamics(event.getTurbineLoadControllerDynamics());

    // ------------------------------------------
    // save with an existing instance
    // ------------------------------------------
    update(entity);

    // ------------------------------------------
    // emit to subscribers that find one
    // ------------------------------------------
    emitFindTurbineLoadControllerDynamics(entity);

    // ------------------------------------------
    // emit to subscribers that find all
    // ------------------------------------------
    emitFindAllTurbineLoadControllerDynamics(entity);
  }

  /*
   * @param	event DeleteTurbineLoadControllerDynamicsEvent
   */
  @EventHandler(payloadType = DeleteTurbineLoadControllerDynamicsEvent.class)
  public void handle(DeleteTurbineLoadControllerDynamicsEvent event) {
    LOGGER.info("handling DeleteTurbineLoadControllerDynamicsEvent - " + event);

    // ------------------------------------------
    // delete delegation
    // ------------------------------------------
    TurbineLoadControllerDynamics entity = delete(event.getTurbineLoadControllerDynamicsId());

    // ------------------------------------------
    // emit to subscribers that find all
    // ------------------------------------------
    emitFindAllTurbineLoadControllerDynamics(entity);
  }

  /*
   * @param	event AssignTurbineLoadControllerDynamicsToTurbineLoadControllerDynamicsEvent
   */
  @EventHandler(
      payloadType = AssignTurbineLoadControllerDynamicsToTurbineLoadControllerDynamicsEvent.class)
  public void handle(
      AssignTurbineLoadControllerDynamicsToTurbineLoadControllerDynamicsEvent event) {
    LOGGER.info(
        "handling AssignTurbineLoadControllerDynamicsToTurbineLoadControllerDynamicsEvent - "
            + event);

    // ------------------------------------------
    // delegate to assignTo
    // ------------------------------------------
    TurbineLoadControllerDynamics entity =
        assignTurbineLoadControllerDynamics(
            event.getTurbineLoadControllerDynamicsId(), event.getAssignment());

    // ------------------------------------------
    // emit to subscribers that find one
    // ------------------------------------------
    emitFindTurbineLoadControllerDynamics(entity);

    // ------------------------------------------
    // emit to subscribers that find all
    // ------------------------------------------
    emitFindAllTurbineLoadControllerDynamics(entity);
  }

  /*
   * @param	event UnAssignTurbineLoadControllerDynamicsFromTurbineLoadControllerDynamicsEvent
   */
  @EventHandler(
      payloadType =
          UnAssignTurbineLoadControllerDynamicsFromTurbineLoadControllerDynamicsEvent.class)
  public void handle(
      UnAssignTurbineLoadControllerDynamicsFromTurbineLoadControllerDynamicsEvent event) {
    LOGGER.info(
        "handling UnAssignTurbineLoadControllerDynamicsFromTurbineLoadControllerDynamicsEvent - "
            + event);

    // ------------------------------------------
    // delegate to unAssignFrom
    // ------------------------------------------
    TurbineLoadControllerDynamics entity =
        unAssignTurbineLoadControllerDynamics(event.getTurbineLoadControllerDynamicsId());

    // ------------------------------------------
    // emit to subscribers that find one
    // ------------------------------------------
    emitFindTurbineLoadControllerDynamics(entity);

    // ------------------------------------------
    // emit to subscribers that find all
    // ------------------------------------------
    emitFindAllTurbineLoadControllerDynamics(entity);
  }

  /**
   * Method to retrieve the TurbineLoadControllerDynamics via an
   * TurbineLoadControllerDynamicsPrimaryKey.
   *
   * @param id Long
   * @return TurbineLoadControllerDynamics
   * @exception ProcessingException - Thrown if processing any related problems
   * @exception IllegalArgumentException
   */
  @SuppressWarnings("unused")
  @QueryHandler
  public TurbineLoadControllerDynamics handle(FindTurbineLoadControllerDynamicsQuery query)
      throws ProcessingException, IllegalArgumentException {
    return find(query.getFilter().getTurbineLoadControllerDynamicsId());
  }

  /**
   * Method to retrieve a collection of all TurbineLoadControllerDynamicss
   *
   * @param query FindAllTurbineLoadControllerDynamicsQuery
   * @return List<TurbineLoadControllerDynamics>
   * @exception ProcessingException Thrown if any problems
   */
  @SuppressWarnings("unused")
  @QueryHandler
  public List<TurbineLoadControllerDynamics> handle(FindAllTurbineLoadControllerDynamicsQuery query)
      throws ProcessingException {
    return findAll(query);
  }

  /**
   * emit to subscription queries of type FindTurbineLoadControllerDynamics, but only if the id
   * matches
   *
   * @param entity TurbineLoadControllerDynamics
   */
  protected void emitFindTurbineLoadControllerDynamics(TurbineLoadControllerDynamics entity) {
    LOGGER.info("handling emitFindTurbineLoadControllerDynamics");

    queryUpdateEmitter.emit(
        FindTurbineLoadControllerDynamicsQuery.class,
        query ->
            query
                .getFilter()
                .getTurbineLoadControllerDynamicsId()
                .equals(entity.getTurbineLoadControllerDynamicsId()),
        entity);
  }

  /**
   * unconditionally emit to subscription queries of type FindAllTurbineLoadControllerDynamics
   *
   * @param entity TurbineLoadControllerDynamics
   */
  protected void emitFindAllTurbineLoadControllerDynamics(TurbineLoadControllerDynamics entity) {
    LOGGER.info("handling emitFindAllTurbineLoadControllerDynamics");

    queryUpdateEmitter.emit(FindAllTurbineLoadControllerDynamicsQuery.class, query -> true, entity);
  }

  // --------------------------------------------------
  // attributes
  // --------------------------------------------------
  @Autowired private final QueryUpdateEmitter queryUpdateEmitter;
  private static final Logger LOGGER =
      Logger.getLogger(TurbineLoadControllerDynamicsProjector.class.getName());
}
