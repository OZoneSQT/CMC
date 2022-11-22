package ast.ast;

public class WhileStatement extends Statement {
	public Expression exp;
	public Statements stats;

	public WhileStatement( Expression exp, Statements stats ) {
		this.exp = exp;
		this.stats = stats;
	}
}