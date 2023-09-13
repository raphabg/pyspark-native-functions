package org.apache.spark.sql
package catalyst.expressions.BalogoFuzzyExpressions

import catalyst.expressions.codegen.{CodegenContext, ExprCode}
import catalyst.expressions.{
  BinaryExpression,
  Expression,
  ImplicitCastInputTypes,
  NullIntolerant
}
import org.apache.spark.sql.catalyst.util.BalogoSimilarityUtils
import types.{AbstractDataType, DataType, DoubleType, StringType}
import org.apache.spark.unsafe.types.UTF8String

case class BalogoJaroWinklerSimilarity(left: Expression, right: Expression)
    extends BinaryExpression
    with ImplicitCastInputTypes
    with NullIntolerant {

  override def inputTypes: Seq[AbstractDataType] = Seq(StringType, StringType)

  override def dataType: DataType = DoubleType

  protected override def nullSafeEval(leftValue: Any, rightValue: Any): Any =
    BalogoSimilarityUtils.balogoJaroWinklerSimilarity(
      left.asInstanceOf[UTF8String],
      right.asInstanceOf[UTF8String]
    )

  override def doGenCode(ctx: CodegenContext, ev: ExprCode): ExprCode = {
    val bsu = BalogoSimilarityUtils.getClass.getName.stripSuffix("$")

    nullSafeCodeGen(
      ctx,
      ev,
      (left, right) =>
        s"${ev.value} = $bsu.balogoJaroWinklerSimilarity($left, $right);"
    )
  }

  override protected def withNewChildrenInternal(
      newLeft: Expression,
      newRight: Expression
  ): Expression = BalogoJaroWinklerSimilarity(newLeft, newRight)
}
