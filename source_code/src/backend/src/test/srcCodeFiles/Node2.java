/**
 * @author Ankita Patel
 */

/**
 * Factory for creating AST nodes
 *
 */
public class NodeFactory {

	/**
	 * factory method for creating an Assignment node
	 * @input : Variable, Expression
	 * @returns: Statement of type Assignment
	 * */
	public Statement makeAssignment(Variable var, Expression exp) {
		return new Assignment(var, exp);
	}

	/**
	 * factory method for creating a Declaration node
	 * @input : Variable
	 * @returns: Statement of type Declaration
	 * */
	public Statement makeDeclaration(Variable var) {
		return new Declaration(var);
	}

	/**
	 * factory method for creating a Sequence node
	 * @input : Sequence of statements
	 * @returns: Statement of type Sequence
	 * */
	public Statement makeSequence(Statement...statements) {
		return new Sequence(statements);
	}

	/**
	 * factory method for creating an InfixExpression node
	 * @input : Operator, 2 Expressions
	 * @returns: Expression of type InfixExpression
	 * */
	public Expression makeInfixExpression(Operator op, Expression ex1, Expression ex2) {
		return new InfixExpression(op, ex1, ex2);
	}

	/**
	 * factory method for creating a NumberExpression node
	 * @input : Integer
	 * @returns: Expression of type NumberExpression
	 * */
	public Expression makeNumberExpression(int number) {
		return new NumberExpression(number);
	}

	/**
	 * factory method for creating a PrefixExpression node
	 * @input : Operator, 2 Expressions
	 * @returns: Expression of type PrefixExpression
	 * */
	public Expression makePrefixExpression(Operator op, Expression ex1, Expression ex2) {
		return new PrefixExpression(op, ex1,ex2);
	}

	/**
	 * factory method for creating a VariableExpression node
	 * @input : Variable
	 * @returns: Expression of type VariableExpression
	 * */
	public Expression makeVariableExpression(Variable var) {
		return new VariableExpression(var);
		//hello world
	}

	/**
	 * factory method for creating a StringExpression node
	 * @input : String
	 * @returns: Expression of type StringExpression
	 * */
	public Expression makeStringExpression(String s) {
		return new StringExpression(s);
	}

}