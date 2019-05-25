import Element.elem

sealed abstract class Expr
case class Var(name: String) extends Expr
case class Number(num: Double) extends Expr
case class UnOp(operator: String, arg: Expr) extends Expr
case class BinOp(operator: String, left: Expr, right: Expr) extends Expr

class ExprFormatter {
  private val operatorGroups =
    Array(
      Set("|", "||"),
      Set("&", "&&"),
      Set("^"),
      Set("==", "!="),
      Set("<", "<=", ">", ">="),
      Set("+", "-"),
      Set("*", "%")
    )

  private val operatorPrecedence = {
    val associations =
      for {
        i  <- 0 until operatorGroups.length
        op <- operatorGroups(i)
      } yield op -> i
        // ^ Infix arrow can be part of map construction but it's also a value in its own right.
        // `op -> i` is just a pair `(op, i)`.
    associations.toMap
  }

  // Precedence for unary operators is higher than all binary operators except for `/`,
  // so ensure precedence is always 1 more than the binary operator with the highest precedence.
  private val unaryPrecedence = operatorGroups.length
  private val fractionPrecedence = -1

  private def format(e: Expr, enclPrec: Int): Element =
    e match {
      case Var(name) => elem(name)

      case Number(num) =>
        def stripDot(s: String) =
          if (s endsWith ".0") s.substring(0, s.length - 2)
          else s
        elem(stripDot(num.toString))

      case UnOp(op, arg) =>
        elem(op) beside format(arg, unaryPrecedence)

      case BinOp("/", left, right) =>
        val top = format(left, fractionPrecedence)
        val bottom = format(right, fractionPrecedence)
        val line = elem('-', top.width max bottom.width, 1)
        val fraction = top above line above bottom
        if (enclPrec != fractionPrecedence) fraction
        else elem(" ") beside fraction beside elem(" ")

      case BinOp(op, left, right) =>
        val opPrec = operatorPrecedence(op)
        val l = format(left, opPrec)
        val r = format(right, opPrec + 1)
        val oper = l beside elem(" " + op + " ") beside r
        if (enclPrec <= opPrec) oper
        else elem("(") beside oper beside elem(")")
    }

  def format(e: Expr): Element = format(e, 0)
}
