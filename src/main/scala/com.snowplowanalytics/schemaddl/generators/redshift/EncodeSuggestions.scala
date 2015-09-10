/*
 * Copyright (c) 2015 Snowplow Analytics Ltd. All rights reserved.
 *
 * This program is licensed to you under the Apache License Version 2.0,
 * and you may not use this file except in compliance with the Apache License Version 2.0.
 * You may obtain a copy of the Apache License Version 2.0 at http://www.apache.org/licenses/LICENSE-2.0.
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the Apache License Version 2.0 is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the Apache License Version 2.0 for the specific language governing permissions and limitations there under.
 */
package com.snowplowanalytics.schemaddl
package generators
package redshift

import scala.annotation.tailrec

object EncodeSuggestions {
  import Ddl._

  /**
   * Type alias for function suggesting an compression encoding based on map of
   * JSON Schema properties
   */
  type EncodingSuggestion = (Map[String, String], DataType, String) => Option[CompressionEncoding]

  // Suggest LZO Encoding for boolean, double precision and real
  val lzoSuggestion: EncodingSuggestion = (properties, dataType, columnName) =>
    dataType match {
      case DataTypes.RedshiftBoolean => Some(CompressionEncodings.RawEncoding)
      case DataTypes.RedshiftDouble => Some(CompressionEncodings.RawEncoding)
      case DataTypes.RedshiftReal => Some(CompressionEncodings.RawEncoding)
      case _ => None
    }


}
