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
package com.occulue.europeanstandards.commongridmodelexchangestandard.equipmentprofile.meas.projector;

import com.occulue.api.*;
import com.occulue.entity.*;
import com.occulue.europeanstandards.commongridmodelexchangestandard.equipmentprofile.meas.repository.*;
import com.occulue.exception.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Projector for StringMeasurement as outlined for the CQRS pattern.
 *
 * <p>Commands are handled by StringMeasurementAggregate
 *
 * @author your_name_here
 */
@Component("stringMeasurement-entity-projector")
public class StringMeasurementEntityProjector {

  // core constructor
  public StringMeasurementEntityProjector(StringMeasurementRepository repository) {
    this.repository = repository;
  }

  /*
   * Insert a StringMeasurement
   *
   * @param	entity StringMeasurement
   */
  public StringMeasurement create(StringMeasurement entity) {
    LOGGER.info("creating " + entity.toString());

    // ------------------------------------------
    // persist a new one
    // ------------------------------------------
    return repository.save(entity);
  }

  /*
   * Update a StringMeasurement
   *
   * @param	entity StringMeasurement
   */
  public StringMeasurement update(StringMeasurement entity) {
    LOGGER.info("updating " + entity.toString());

    // ------------------------------------------
    // save with an existing instance
    // ------------------------------------------
    return repository.save(entity);
  }

  /*
   * Delete a StringMeasurement
   *
   * @param	id		UUID
   */
  public StringMeasurement delete(UUID id) {
    LOGGER.info("deleting " + id.toString());

    // ------------------------------------------
    // locate the entity by the provided id
    // ------------------------------------------
    StringMeasurement entity = repository.findById(id).get();

    // ------------------------------------------
    // delete what is discovered
    // ------------------------------------------
    repository.delete(entity);

    return entity;
  }

  /**
   * Method to retrieve the StringMeasurement via an FindStringMeasurementQuery
   *
   * @return query FindStringMeasurementQuery
   */
  @SuppressWarnings("unused")
  public StringMeasurement find(UUID id) {
    LOGGER.info("handling find using " + id.toString());
    try {
      return repository.findById(id).get();
    } catch (IllegalArgumentException exc) {
      LOGGER.log(Level.WARNING, "Failed to find a StringMeasurement - {0}", exc.getMessage());
    }
    return null;
  }

  /**
   * Method to retrieve a collection of all StringMeasurements
   *
   * @param query FindAllStringMeasurementQuery
   * @return List<StringMeasurement>
   */
  @SuppressWarnings("unused")
  public List<StringMeasurement> findAll(FindAllStringMeasurementQuery query) {
    LOGGER.info("handling findAll using " + query.toString());
    try {
      return repository.findAll();
    } catch (IllegalArgumentException exc) {
      LOGGER.log(Level.WARNING, "Failed to find all StringMeasurement - {0}", exc.getMessage());
    }
    return null;
  }

  // --------------------------------------------------
  // attributes
  // --------------------------------------------------
  @Autowired protected final StringMeasurementRepository repository;

  private static final Logger LOGGER =
      Logger.getLogger(StringMeasurementEntityProjector.class.getName());
}
