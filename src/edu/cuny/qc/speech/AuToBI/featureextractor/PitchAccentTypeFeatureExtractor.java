/*  PitchAccentTypeFeatureExtractor.java

    Copyright (c) 2009-2014 Andrew Rosenberg

  This file is part of the AuToBI prosodic analysis package.

  AuToBI is free software: you can redistribute it and/or modify
  it under the terms of the Apache License (see boilerplate below)

 ***********************************************************************************************************************
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You should have received a copy of the Apache 2.0 License along with AuToBI.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 ***********************************************************************************************************************
 */
package edu.cuny.qc.speech.AuToBI.featureextractor;

import edu.cuny.qc.speech.AuToBI.util.ToBIUtils;
import edu.cuny.qc.speech.AuToBI.core.Word;
import edu.cuny.qc.speech.AuToBI.core.FeatureExtractor;

import java.util.List;

/**
 * PitchAccentTypeFeatureExtractor extracts ground truth labels for the type of pitch accent (if any) on each region.
 */
public class PitchAccentTypeFeatureExtractor extends FeatureExtractor {
  public static final String moniker = "nominal_PitchAccentType";
  private String feature;  // the destination feature

  /**
   * Cosntructs a new PitchAccentTypeFeatureExtractor.
   *
   * @param feature the destination feature to store the pitch accent type.
   */
  @Deprecated
  public PitchAccentTypeFeatureExtractor(String feature) {
    this.feature = feature;
    extracted_features.add(feature);
  }

  public PitchAccentTypeFeatureExtractor() {
    this.feature = moniker;
    extracted_features.add(feature);
  }

  /**
   * Extracts the ground truth pitch accent typ annotation to the destination feature name for each region.
   *
   * @param regions The regions to extract features from.
   * @throws FeatureExtractorException if there is a problem.
   */
  public void extractFeatures(List regions) throws FeatureExtractorException {
    ToBIUtils.setPitchAccentType((List<Word>) regions, feature);
  }
}