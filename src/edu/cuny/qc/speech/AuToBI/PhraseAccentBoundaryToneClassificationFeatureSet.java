/*  PhraseAccentBoundaryToneClassificationFeatureSet.java

    Copyright (c) 2009-2010 Andrew Rosenberg

    This file is part of the AuToBI prosodic analysis package.

    AuToBI is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    AuToBI is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with AuToBI.  If not, see <http://www.gnu.org/licenses/>.
 */package edu.cuny.qc.speech.AuToBI;

/**
 * PhraseAccentBoundaryToneClassificationFeatureSet is responsible for describing the features necessary to perform
 * classification of intonational phrase ending tones -- phrase accents and boundary tones.
 */
public class PhraseAccentBoundaryToneClassificationFeatureSet extends FeatureSet {

  /**
   * Constructs a new PhraseAccentBoundaryToneClassificationFeatureSet.
   */
  PhraseAccentBoundaryToneClassificationFeatureSet() {
    super();

     required_features.add("duration__duration");

     for (String acoustic : new String[]{"f0", "I"}) {
       for (String norm : new String[]{"", "_norm"}) {
         for (String slope : new String[]{"", "_delta"}) {
           for (String agg : new String[]{"max", "mean", "stdev", "zMax"}) {
             required_features.add(acoustic + slope + norm + "__" + agg + "_200ms");
           }
         }
       }
     }

     class_attribute = "nominal_PhraseAccentBoundaryTone";
  }
}