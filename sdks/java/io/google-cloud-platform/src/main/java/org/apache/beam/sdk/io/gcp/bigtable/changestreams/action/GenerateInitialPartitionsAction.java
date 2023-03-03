/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.beam.sdk.io.gcp.bigtable.changestreams.action;

import com.google.cloud.Timestamp;
import javax.annotation.Nullable;
import org.apache.beam.sdk.annotations.Internal;
import org.apache.beam.sdk.io.gcp.bigtable.changestreams.ChangeStreamMetrics;
import org.apache.beam.sdk.io.gcp.bigtable.changestreams.dao.ChangeStreamDao;
import org.apache.beam.sdk.io.gcp.bigtable.changestreams.model.PartitionRecord;
import org.apache.beam.sdk.transforms.DoFn.OutputReceiver;
import org.apache.beam.sdk.transforms.splittabledofn.ManualWatermarkEstimator;
import org.joda.time.Instant;

/**
 * Class to generate first set of outputs for {@link
 * org.apache.beam.sdk.io.gcp.bigtable.changestreams.dofn.DetectNewPartitionsDoFn}.
 */
@SuppressWarnings({"UnusedVariable", "UnusedMethod"})
@Internal
public class GenerateInitialPartitionsAction {
  private final ChangeStreamMetrics metrics;
  private final ChangeStreamDao changeStreamDao;
  @Nullable private final Timestamp endTime;

  public GenerateInitialPartitionsAction(
      ChangeStreamMetrics metrics, ChangeStreamDao changeStreamDao, @Nullable Timestamp endTime) {
    this.metrics = metrics;
    this.changeStreamDao = changeStreamDao;
    this.endTime = endTime;
  }

  /**
   * The very first step of the pipeline when there are no partitions being streamed yet. We want to
   * get an initial list of partitions to stream and output them.
   *
   * @return true if this pipeline should continue, otherwise false.
   */
  public boolean run(
      OutputReceiver<PartitionRecord> receiver,
      ManualWatermarkEstimator<Instant> watermarkEstimator,
      Timestamp startTime) {
    return true;
  }
}