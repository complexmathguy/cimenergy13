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
package com.occulue.europeanstandards.commongridmodelexchangestandard.dynamicsprofile.standardmodels.powersystemstabilizerdynamics.subscriber;

import com.occulue.api.*;
import com.occulue.entity.*;
import com.occulue.exception.*;
import com.occulue.subscriber.*;
import java.util.*;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.queryhandling.SubscriptionQueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.stereotype.Component;

/**
 * Subscriber for Pss1 related events. .
 *
 * @author your_name_here
 */
@Component("pss1-subscriber")
public class Pss1Subscriber extends BaseSubscriber {

  public Pss1Subscriber() {
    queryGateway = applicationContext.getBean(QueryGateway.class);
  }

  public SubscriptionQueryResult<List<Pss1>, Pss1> pss1Subscribe() {
    return queryGateway.subscriptionQuery(
        new FindAllPss1Query(),
        ResponseTypes.multipleInstancesOf(Pss1.class),
        ResponseTypes.instanceOf(Pss1.class));
  }

  public SubscriptionQueryResult<Pss1, Pss1> pss1Subscribe(@DestinationVariable UUID pss1Id) {
    return queryGateway.subscriptionQuery(
        new FindPss1Query(new LoadPss1Filter(pss1Id)),
        ResponseTypes.instanceOf(Pss1.class),
        ResponseTypes.instanceOf(Pss1.class));
  }

  // -------------------------------------------------
  // attributes
  // -------------------------------------------------
  @Autowired private final QueryGateway queryGateway;
}
