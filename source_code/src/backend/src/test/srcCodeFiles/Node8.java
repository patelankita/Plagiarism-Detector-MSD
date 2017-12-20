public class NodeFactory {


	public Statement makeAssignment(Variable var, Expression exp) {
		return new Assignment(var, exp);
	}

	public Statement makeDeclaration(Variable var) {
		return new Declaration(var);
	}


	public Statement makeSequence(Statement...statements) {
		return new Sequence(statements);
	}


	public Expression makeInfixExpression(Operator op, Expression ex1, Expression ex2) {
		return new InfixExpression(op, ex1, ex2);
	}


	public Expression makeNumberExpression(int number) {
		return new NumberExpression(number);
	}


	public Expression makePrefixExpression(Operator op, Expression ex1, Expression ex2) {
		return new PrefixExpression(op, ex1,ex2);
	}


	public Expression makeVariableExpression(Variable var) {
		return new VariableExpression(var);
	}


	public Expression makeStringExpression(String s) {
		return new StringExpression(s);
	}

}