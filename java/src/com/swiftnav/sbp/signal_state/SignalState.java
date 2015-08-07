/*
 * Copyright (C) 2015 Swift Navigation Inc.
 * Contact: Gareth McMullin <gareth@swiftnav.com>
 * Contact: Bhaskar Mookerji <mookerji@swiftnav.com>
 *
 * This source is subject to the license found in the file 'LICENSE' which must
 * be be distributed together with this source. All other rights reserved.
 *
 * THIS CODE AND INFORMATION IS PROVIDED "AS IS" WITHOUT WARRANTY OF ANY KIND,
 * EITHER EXPRESSED OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND/OR FITNESS FOR A PARTICULAR PURPOSE.
 */

package com.swiftnav.sbp.signal_state;

import com.swiftnav.sbp.SBPMessage;
import com.swiftnav.sbp.SBPBinaryException;
import com.swiftnav.sbp.SBPStruct;

import org.json.JSONObject;
import org.json.JSONArray;
import com.swiftnav.sbp.SBPStruct;

public class SignalState extends SBPStruct {
    
    /** Constellation from which the sattelite belongs to. */
    public int constellation;
    
    /** Band of the satellite. */
    public int band;
    
    /** PRN of the satellite. */
    public int prn;
    

    public SignalState () {}

    @Override
    protected SignalState parse(SBPMessage.Parser parser) throws SBPBinaryException {
        /* Parse fields from binary */
        constellation = parser.getU8();
        band = parser.getU8();
        prn = parser.getU16();
        return this;
    }

    @Override
    protected void build(SBPMessage.Builder builder) {
        /* Build fields into binary */
        builder.putU8(constellation);
        builder.putU8(band);
        builder.putU16(prn);
    }

    @Override
    protected JSONObject toJSON() {
        JSONObject obj = new JSONObject();
        obj.put("constellation", constellation);
        obj.put("band", band);
        obj.put("prn", prn);
        return obj;
    }
}