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
package com.occulue.europeanstandards.commongridmodelexchangestandard.equipmentprofile.wires.projector;

import com.occulue.api.*;
import com.occulue.entity.*;
import com.occulue.europeanstandards.commongridmodelexchangestandard.equipmentprofile.wires.repository.*;
import com.occulue.exception.*;
import java.util.*;
import java.util.logging.Logger;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.axonframework.queryhandling.QueryUpdateEmitter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Projector for Conductor as outlined for the CQRS pattern. All event handling and query handling
 * related to Conductor are invoked here and dispersed as an event to be handled elsewhere.
 *
 * <p>Commands are handled by ConductorAggregate
 *
 * @author your_name_here
 */
// @ProcessingGroup("conductor")
@Component("conductor-projector")
public class ConductorProjector extends ConductorEntityProjector {

  // core constructor
  public ConductorProjector(ConductorRepository repository, QueryUpdateEmitter queryUpdateEmitter) {
    super(repository);
    this.queryUpdateEmitter = queryUpdateEmitter;
  }

  /*
   * @param	event CreateConductorEvent
   */
  @EventHandler(payloadType = CreateConductorEvent.class)
  public void handle(CreateConductorEvent event) {
    LOGGER.info("handling CreateConductorEvent - " + event);
    Conductor entity = new Conductor();
    entity.setConductorId(event.getConductorId());

    // ------------------------------------------
    // persist a new one
    // ------------------------------------------
    create(entity);

    // ------------------------------------------
    // emit to subscribers that find all
    // ------------------------------------------
    emitFindAllConductor(entity);
  }

  /*
   * @param	event UpdateConductorEvent
   */
  @EventHandler(payloadType = UpdateConductorEvent.class)
  public void handle(UpdateConductorEvent event) {
    LOGGER.info("handling UpdateConductorEvent - " + event);

    Conductor entity = new Conductor();
    entity.setConductorId(event.getConductorId());
    entity.setLength(event.getLength());

    // ------------------------------------------
    // save with an existing instance
    // ------------------------------------------
    update(entity);

    // ------------------------------------------
    // emit to subscribers that find one
    // ------------------------------------------
    emitFindConductor(entity);

    // ------------------------------------------
    // emit to subscribers that find all
    // ------------------------------------------
    emitFindAllConductor(entity);
  }

  /*
   * @param	event DeleteConductorEvent
   */
  @EventHandler(payloadType = DeleteConductorEvent.class)
  public void handle(DeleteConductorEvent event) {
    LOGGER.info("handling DeleteConductorEvent - " + event);

    // ------------------------------------------
    // delete delegation
    // ------------------------------------------
    Conductor entity = delete(event.getConductorId());

    // ------------------------------------------
    // emit to subscribers that find all
    // ------------------------------------------
    emitFindAllConductor(entity);
  }

  /*
   * @param	event AssignLengthToConductorEvent
   */
  @EventHandler(payloadType = AssignLengthToConductorEvent.class)
  public void handle(AssignLengthToConductorEvent event) {
    LOGGER.info("handling AssignLengthToConductorEvent - " + event);

    // ------------------------------------------
    // delegate to assignTo
    // ------------------------------------------
    Conductor entity = assignLength(event.getConductorId(), event.getAssignment());

    // ------------------------------------------
    // emit to subscribers that find one
    // ------------------------------------------
    emitFindConductor(entity);

    // ------------------------------------------
    // emit to subscribers that find all
    // ------------------------------------------
    emitFindAllConductor(entity);
  }

  /*
   * @param	event UnAssignLengthFromConductorEvent
   */
  @EventHandler(payloadType = UnAssignLengthFromConductorEvent.class)
  public void handle(UnAssignLengthFromConductorEvent event) {
    LOGGER.info("handling UnAssignLengthFromConductorEvent - " + event);

    // ------------------------------------------
    // delegate to unAssignFrom
    // ------------------------------------------
    Conductor entity = unAssignLength(event.getConductorId());

    // ------------------------------------------
    // emit to subscribers that find one
    // ------------------------------------------
    emitFindConductor(entity);

    // ------------------------------------------
    // emit to subscribers that find all
    // ------------------------------------------
    emitFindAllConductor(entity);
  }

  /**
   * Method to retrieve the Conductor via an ConductorPrimaryKey.
   *
   * @param id Long
   * @return Conductor
   * @exception ProcessingException - Thrown if processing any related problems
   * @exception IllegalArgumentException
   */
  @SuppressWarnings("unused")
  @QueryHandler
  public Conductor handle(FindConductorQuery query)
      throws ProcessingException, IllegalArgumentException {
    return find(query.getFilter().getConductorId());
  }

  /**
   * Method to retrieve a collection of all Conductors
   *
   * @param query FindAllConductorQuery
   * @return List<Conductor>
   * @exception ProcessingException Thrown if any problems
   */
  @SuppressWarnings("unused")
  @QueryHandler
  public List<Conductor> handle(FindAllConductorQuery query) throws ProcessingException {
    return findAll(query);
  }

  /**
   * emit to subscription queries of type FindConductor, but only if the id matches
   *
   * @param entity Conductor
   */
  protected void emitFindConductor(Conductor entity) {
    LOGGER.info("handling emitFindConductor");

    queryUpdateEmitter.emit(
        FindConductorQuery.class,
        query -> query.getFilter().getConductorId().equals(entity.getConductorId()),
        entity);
  }

  /**
   * unconditionally emit to subscription queries of type FindAllConductor
   *
   * @param entity Conductor
   */
  protected void emitFindAllConductor(Conductor entity) {
    LOGGER.info("handling emitFindAllConductor");

    queryUpdateEmitter.emit(FindAllConductorQuery.class, query -> true, entity);
  }

  // --------------------------------------------------
  // attributes
  // --------------------------------------------------
  @Autowired private final QueryUpdateEmitter queryUpdateEmitter;
  private static final Logger LOGGER = Logger.getLogger(ConductorProjector.class.getName());
}
