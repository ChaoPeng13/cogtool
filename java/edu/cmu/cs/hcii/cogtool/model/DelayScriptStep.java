/*******************************************************************************
 * CogTool Copyright Notice and Distribution Terms
 * CogTool 1.2, Copyright (c) 2005-2013 Carnegie Mellon University
 * This software is distributed under the terms of the FSF Lesser
 * Gnu Public License (see LGPL.txt). 
 * 
 * CogTool is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 2.1 of the License, or
 * (at your option) any later version.
 * 
 * CogTool is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with CogTool; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 * 
 * CogTool makes use of several third-party components, with the 
 * following notices:
 * 
 * Eclipse SWT
 * Eclipse GEF Draw2D
 * 
 * Unless otherwise indicated, all Content made available by the Eclipse 
 * Foundation is provided to you under the terms and conditions of the Eclipse 
 * Public License Version 1.0 ("EPL"). A copy of the EPL is provided with this 
 * Content and is also available at http://www.eclipse.org/legal/epl-v10.html.
 * 
 * CLISP
 * 
 * Copyright (c) Sam Steingold, Bruno Haible 2001-2006
 * This software is distributed under the terms of the FSF Gnu Public License.
 * See COPYRIGHT file in clisp installation folder for more information.
 * 
 * ACT-R 6.0
 * 
 * Copyright (c) 1998-2007 Dan Bothell, Mike Byrne, Christian Lebiere & 
 *                         John R Anderson. 
 * This software is distributed under the terms of the FSF Lesser
 * Gnu Public License (see LGPL.txt).
 * 
 * Apache Jakarta Commons-Lang 2.1
 * 
 * This product contains software developed by the Apache Software Foundation
 * (http://www.apache.org/)
 * 
 * jopt-simpler
 * 
 * Copyright (c) 2004-2013 Paul R. Holser, Jr.
 * 
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 * 
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 * 
 * Mozilla XULRunner 1.9.0.5
 * 
 * The contents of this file are subject to the Mozilla Public License
 * Version 1.1 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/.
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 * 
 * The J2SE(TM) Java Runtime Environment
 * 
 * Copyright 2009 Sun Microsystems, Inc., 4150
 * Network Circle, Santa Clara, California 95054, U.S.A.  All
 * rights reserved. U.S.  
 * See the LICENSE file in the jre folder for more information.
 ******************************************************************************/

package edu.cmu.cs.hcii.cogtool.model;

import edu.cmu.cs.hcii.cogtool.util.L10N;
import edu.cmu.cs.hcii.cogtool.util.NumberUtils;
import edu.cmu.cs.hcii.cogtool.util.ObjectLoader;
import edu.cmu.cs.hcii.cogtool.util.ObjectSaver;

/**
 * @author alexeiser
 */
public class DelayScriptStep extends AScriptStep
{
    public static final int edu_cmu_cs_hcii_cogtool_model_DelayScriptStep_version = 0;

    protected static final String delayDurationVAR = "duration";
    protected static final String labelVAR = "label";

    protected double delayDuration;
    protected String label = DelayScriptStep.DEFAULT_DELAY_LABEL;

    public static final String DEFAULT_DELAY_LABEL =
    TransitionDelay.DEFAULT_DELAY_LABEL;

    public static final double DEFAULT_DELAY_DURATION = 1.0;

    private static ObjectSaver.IDataSaver<DelayScriptStep> SAVER =
        new ObjectSaver.ADataSaver<DelayScriptStep>() {
            @Override
            public int getVersion()
            {
                return edu_cmu_cs_hcii_cogtool_model_DelayScriptStep_version;
            }

            @Override
            public void saveData(DelayScriptStep v, ObjectSaver saver)
                throws java.io.IOException
            {
                saver.saveDouble(v.delayDuration, delayDurationVAR);
                saver.saveString(v.label, labelVAR);
            }
        };

    public static void registerSaver()
    {
        ObjectSaver.registerSaver(DelayScriptStep.class.getName(), SAVER);
    }

    private static ObjectLoader.IObjectLoader<DelayScriptStep> LOADER =
        new ObjectLoader.AObjectLoader<DelayScriptStep>() {
            @Override
            public DelayScriptStep createObject()
            {
                return new DelayScriptStep();
            }

            @Override
            public void set(DelayScriptStep target, String variable, double value)
            {
                if (variable != null) {
                    if (variable.equals(delayDurationVAR)) {
                        target.delayDuration = value;
                    }
                }
            }

            @Override
            public void set(DelayScriptStep target, String variable, Object value)
            {
                if (variable != null) {
                    if (variable.equals(labelVAR)) {
                        target.label = (String) value;
                    }
                }
            }
        };

    public static void registerLoader()
    {
        ObjectLoader.registerLoader(DelayScriptStep.class.getName(),
                                    edu_cmu_cs_hcii_cogtool_model_DelayScriptStep_version,
                                    LOADER);
    }

    /**
     * This represents insertion by the user.
     */
    public DelayScriptStep(Frame current, double dur, String lbl)
    {
        super(current);

        delayDuration = dur;
        label = lbl;
    }

    /**
     * Automatically inserted
     */
    public DelayScriptStep(AScriptStep ownerStep, double dur)
    {
        super(ownerStep, false);

        delayDuration = dur;
        label = DelayScriptStep.DEFAULT_DELAY_LABEL;
    }

    // For loading
    protected DelayScriptStep()
    {
        // For steps saved before b22:
        // this.demonstration starts out as false; it gets set to true
        // in AScriptStep's LOADER if the owner is this step.
    }

    public double getDelayDuration()
    {
        return delayDuration;
    }

    public void setDelayDuration(double newDuration)
    {
        if (delayDuration != newDuration) {
            delayDuration = newDuration;
        }
    }

    /**
     * Compute and return the user viewable string describing
     * what this demonstration step represents (without source widget
     * or destination frame).
     */
    @Override
    public String getLocalizedString()
    {
        return label + " " + L10N.get("TA.for", "for") + " " +
            NumberUtils.formatSeconds(delayDuration);
    }

    /**
     * Create a "deep" copy of this script step of an Demonstration.
     * <p>
     * It is the responsibility of the caller to "place" the copy
     * (usually by adding it to an Demonstration).
     *
     * @param duplicateScope used to find design components referred to by the
     *                       step duplicate
     * @return the script step copy
     * @author mlh
     */
    @Override
    public AScriptStep duplicate(TaskApplication.DemoDuplicateScope duplicateScope)
    {
        AScriptStep copy =
            new DelayScriptStep(duplicateScope.getFrame(getCurrentFrame()),
                                delayDuration,
                                label);

        copy.copyState(this);

        return copy;
    }

    /**
     * Create a "deep" copy of this generated script step (i.e., one that is
     * not an owner itself.
     * <p>
     * It is the responsibility of the caller to "place" the copy
     * (usually by adding it to an Demonstration).
     *
     * @param duplicateScope used to find design components referred to by the
     *                       step duplicate
     * @param ownerScope used to find the owner of the duplicate generated step
     * @return the script step copy
     * @author mlh
     */
    @Override
    public AScriptStep duplicate(TaskApplication.DemoDuplicateScope duplicateScope,
                                 AScriptStep.GeneratedStepDuplicateScope ownerScope)
    {
        DelayScriptStep copy =
            new DelayScriptStep(ownerScope.getOwner(owner),
                                delayDuration);

        copy.setLabel(label);
        copy.copyState(this);

        return copy;
    }

    @Override
    public void accept(AScriptStep.ScriptStepVisitor visitor)
    {
        visitor.visit(this);
    }

    public String getLabel()
    {
        return label;
    }

    public void setLabel(String lbl)
    {
        label = lbl;
    }
}