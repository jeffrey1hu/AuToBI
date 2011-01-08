/*  IntermediatePhraseBoundaryTrainer.java

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
 */
package edu.cuny.qc.speech.AuToBI;

import edu.cuny.qc.speech.AuToBI.classifier.AuToBIClassifier;
import edu.cuny.qc.speech.AuToBI.classifier.WekaClassifier;
import edu.cuny.qc.speech.AuToBI.featureset.IntermediatePhraseBoundaryDetectionFeatureSet;
import edu.cuny.qc.speech.AuToBI.featureset.PitchAccentDetectionFeatureSet;
import edu.cuny.qc.speech.AuToBI.util.AuToBIUtils;

import java.util.Collection;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileNotFoundException;

import weka.classifiers.functions.Logistic;

/**
 * IntermediatePhraseBoundaryDetectionTrainer is used to train and serialize models that distinguish (intonational
 * phrase medial) intermediate phrase boundaries from phrase internal word boundaries.
 */
public class IntermediatePhraseBoundaryDetectionTrainer extends AuToBITrainer {
                      /**
   * Constructs a new AuToBITrainer with an associated AuToBI object to manage parameters and feature extraction.
   *
   * @param autobi an AuToBI object.
   */
  public IntermediatePhraseBoundaryDetectionTrainer(AuToBI autobi) {
    super(autobi);
  }

  /**
   * Trains a PitchAccentDetection classifier.
   *
   * @param filenames The filenames to use for training
   * @return A classifier to detect pitch accents
   * @throws Exception if there is a problem with the classifier training.
   */
  public AuToBIClassifier trainClassifier(Collection<String> filenames) throws Exception {
    IntermediatePhraseBoundaryDetectionFeatureSet padfs = new IntermediatePhraseBoundaryDetectionFeatureSet();
    AuToBIClassifier classifier = new WekaClassifier(new Logistic());

    trainClassifier(filenames, padfs, classifier);
    return classifier;
  }

  public static void main(String[] args) {
  AuToBI autobi = new AuToBI();
    autobi.init(args);

    IntermediatePhraseBoundaryDetectionTrainer trainer = new IntermediatePhraseBoundaryDetectionTrainer(autobi);

    try {
      String model_file = autobi.getParameter("model_file");
      AuToBIClassifier classifier =
          trainer.trainClassifier(AuToBIUtils.glob(autobi.getParameter("training_filenames")));

      AuToBIUtils.log("writing model to: " + model_file);
      FileOutputStream fos;
      ObjectOutputStream out;
      try {
        fos = new FileOutputStream(model_file);
        out = new ObjectOutputStream(fos);
        out.writeObject(classifier);
        out.close();
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
