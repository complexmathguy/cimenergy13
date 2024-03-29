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
package com.occulue.europeanstandards.commongridmodelexchangestandard.statevariablesprofile.statevariables.controller.query;

import com.occulue.api.*;
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
 * Implements Spring Controller query CQRS processing for entity SvTapStep.
 *
 * @author your_name_here
 */
@CrossOrigin
@RestController
@RequestMapping("/SvTapStep")
public class SvTapStepQueryRestController extends BaseSpringRestController {

  /**
   * Handles loading a SvTapStep using a UUID
   *
   * @param UUID svTapStepId
   * @return SvTapStep
   */
  @GetMapping("/load")
  public SvTapStep load(@RequestParam(required = true) UUID svTapStepId) {
    SvTapStep entity = null;

    try {
      entity =
          SvTapStepBusinessDelegate.getSvTapStepInstance()
              .getSvTapStep(new SvTapStepFetchOneSummary(svTapStepId));
    } catch (Throwable exc) {
      LOGGER.log(Level.WARNING, "failed to load SvTapStep using Id " + svTapStepId);
      return null;
    }

    return entity;
  }

  /**
   * Handles loading all SvTapStep business objects
   *
   * @return Set<SvTapStep>
   */
  @GetMapping("/")
  public List<SvTapStep> loadAll() {
    List<SvTapStep> svTapStepList = null;

    try {
      // load the SvTapStep
      svTapStepList = SvTapStepBusinessDelegate.getSvTapStepInstance().getAllSvTapStep();

      if (svTapStepList != null) LOGGER.log(Level.INFO, "successfully loaded all SvTapSteps");
    } catch (Throwable exc) {
      LOGGER.log(Level.WARNING, "failed to load all SvTapSteps ", exc);
      return null;
    }

    return svTapStepList;
  }

  // ************************************************************************
  // Attributes
  // ************************************************************************
  protected SvTapStep svTapStep = null;
  private static final Logger LOGGER =
      Logger.getLogger(SvTapStepQueryRestController.class.getName());
}
