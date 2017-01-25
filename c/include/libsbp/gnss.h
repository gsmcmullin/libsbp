/*
 * Copyright (C) 2015 Swift Navigation Inc.
 * Contact: Fergus Noble <fergus@swift-nav.com>
 *
 * This source is subject to the license found in the file 'LICENSE' which must
 * be be distributed together with this source. All other rights reserved.
 *
 * THIS CODE AND INFORMATION IS PROVIDED "AS IS" WITHOUT WARRANTY OF ANY KIND,
 * EITHER EXPRESSED OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND/OR FITNESS FOR A PARTICULAR PURPOSE.
 */

/*****************************************************************************
 * Automatically generated from yaml/swiftnav/sbp/gnss.yaml
 * with generate.py. Please do not hand edit!
 *****************************************************************************/

/** \defgroup gnss Gnss
 *
 * * Various structs shared between modules
 * \{ */

#ifndef LIBSBP_GNSS_MESSAGES_H
#define LIBSBP_GNSS_MESSAGES_H

#include "common.h"


/** Represents all the relevant information about the signal
 *
 * Signal identifier containing constellation, band, and satellite identifier
 */
typedef struct __attribute__((packed)) {
  u8 sat;     /**< Constellation-specific satellite identifier */
  u8 code;    /**< Signal constellation, band and code */
} gnss_signal16_t;


/** Represents all the relevant information about the signal
 *
 * Signal identifier containing constellation, band, and satellite identifier
 */
typedef struct __attribute__((packed)) {
  u16 sat;         /**< Constellation-specific satellite identifier.

Note: unlike GnssSignal16, GPS satellites are encoded as
(PRN - 1). Other constellations do not have this offset.
 */
  u8 code;        /**< Signal constellation, band and code */
  u8 reserved;    /**< Reserved */
} sbp_gnss_signal_t;


/** Millisecond-accurate GPS time
 *
 * A wire-appropriate GPS time, defined as the number of
 * milliseconds since beginning of the week on the Saturday/Sunday
 * transition.
 */
typedef struct __attribute__((packed)) {
  u32 tow;    /**< Milliseconds since start of GPS week [ms] */
  u16 wn;     /**< GPS week number [week] */
} sbp_gps_time_t;


/** Nanosecond-accurate receiver clock time
 *
 * A wire-appropriate receiver clock time, defined as the time
 * since the beginning of the week on the Saturday/Sunday
 * transition. In most cases, observations are epoch aligned
 * so ns field will be 0.
 */
typedef struct __attribute__((packed)) {
  u32 tow;    /**< Milliseconds since start of GPS week [ms] */
  s32 ns;     /**< Nanosecond residual of millisecond-rounded TOW (ranges
from -500000 to 500000)
 [ns] */
  u16 wn;     /**< GPS week number [week] */
} gps_time_nano_t;


/** GNSS carrier phase measurement.
 *
 * Carrier phase measurement in cycles represented as a 40-bit
 * fixed point number with Q32.8 layout, i.e. 32-bits of whole
 * cycles and 8-bits of fractional cycles. This phase has the
 * same sign as the pseudorange.
 */
typedef struct __attribute__((packed)) {
  s32 i;    /**< Carrier phase whole cycles [cycles] */
  u8 f;    /**< Carrier phase fractional part [cycles / 256] */
} carrier_phase_t;


/** \} */

#endif /* LIBSBP_GNSS_MESSAGES_H */