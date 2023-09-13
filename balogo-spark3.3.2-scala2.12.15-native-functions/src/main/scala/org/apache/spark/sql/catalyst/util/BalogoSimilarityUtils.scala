package org.apache.spark.sql
package catalyst.util

import org.apache.commons.text.similarity.JaroWinklerSimilarity
import org.apache.spark.unsafe.types.UTF8String

object BalogoSimilarityUtils {
  def balogoJaroWinklerSimilarity(left: UTF8String, right: UTF8String): Double = {
    new JaroWinklerSimilarity().apply(left.toString(), right.toString())
  }
}
