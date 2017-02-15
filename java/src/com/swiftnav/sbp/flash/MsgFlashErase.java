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

package com.swiftnav.sbp.flash;

import com.swiftnav.sbp.SBPMessage;
import com.swiftnav.sbp.SBPBinaryException;
import com.swiftnav.sbp.SBPStruct;
import java.math.BigInteger;

import org.json.JSONObject;
import org.json.JSONArray;


/** SBP class for message MSG_FLASH_ERASE (0x00E2).
 *
 * You can have MSG_FLASH_ERASE inherent its fields directly from
 * an inherited SBP object, or construct it inline using a dict of its
 * fields.
 *
 * The flash erase message from the host erases a sector of either
 * the STM or M25 onboard flash memory. The device will reply with a
 * MSG_FLASH_DONE message containing the return code - FLASH_OK (0)
 * on success or FLASH_INVALID_FLASH (1) if the flash specified is
 * invalid. */

public class MsgFlashErase extends SBPMessage {
    public static final int TYPE = 0x00E2;

    
    /** Target flags */
    public int target;
    
    /** Flash sector number to erase (0-11 for the STM, 0-15 for
the M25)
 */
    public long sector_num;
    

    public MsgFlashErase (int sender) { super(sender, TYPE); }
    public MsgFlashErase () { super(TYPE); }
    public MsgFlashErase (SBPMessage msg) throws SBPBinaryException {
        super(msg);
        assert msg.type != TYPE;
    }

    @Override
    protected void parse(Parser parser) throws SBPBinaryException {
        /* Parse fields from binary */
        target = parser.getU8();
        sector_num = parser.getU32();
    }

    @Override
    protected void build(Builder builder) {
        builder.putU8(target);
        builder.putU32(sector_num);
    }

    @Override
    public JSONObject toJSON() {
        JSONObject obj = super.toJSON();
        obj.put("target", target);
        obj.put("sector_num", sector_num);
        return obj;
    }
}