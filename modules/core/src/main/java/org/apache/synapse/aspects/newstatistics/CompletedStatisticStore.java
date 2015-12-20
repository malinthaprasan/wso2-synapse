/*
 *   Copyright (c) 2015, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *   WSO2 Inc. licenses this file to you under the Apache License,
 *   Version 2.0 (the "License"); you may not use this file except
 *   in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */

package org.apache.synapse.aspects.newstatistics;

import java.util.LinkedList;
import java.util.List;

/**
 * This class will hold completed statistic entries till they are collected for storage.
 */
public class CompletedStatisticStore {

	private final List<List<StatisticsLog>> completedStatisticEntries = new LinkedList<>();
	private final List<EndpointStatisticLog> completedEndpointStatisticEntries = new LinkedList<>();

	public List<List<StatisticsLog>> getCompletedStatisticEntries() {
		List<List<StatisticsLog>> cloneOfCompletedStatisticEntries = new LinkedList<>();
		synchronized (completedStatisticEntries) {
			cloneOfCompletedStatisticEntries.addAll(completedStatisticEntries);
			completedStatisticEntries.clear();
		}
		return cloneOfCompletedStatisticEntries;
	}

	public void putCompletedStatisticEntry(List<StatisticsLog> statisticsLogs) {
		synchronized (completedStatisticEntries) {
			completedStatisticEntries.add(statisticsLogs);
		}
	}

	public List<EndpointStatisticLog> getCompletedEndpointStatisticEntries() {
		List<EndpointStatisticLog>  cloneOfCompletedEndpointEntries = new LinkedList<>();
		synchronized (completedEndpointStatisticEntries) {
			cloneOfCompletedEndpointEntries.addAll(completedEndpointStatisticEntries);
			completedEndpointStatisticEntries.clear();
		}
		return cloneOfCompletedEndpointEntries;
	}

	public void putCompletedEndpointStatisticEntry(EndpointStatisticLog endpointStatisticLog) {
		synchronized (completedEndpointStatisticEntries) {
			completedEndpointStatisticEntries.add(endpointStatisticLog);
		}
	}
}
