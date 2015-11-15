package yoshikihigo.clonegear.data;

import java.util.ArrayList;
import java.util.List;

import yoshikihigo.clonegear.LANGUAGE;
import yoshikihigo.clonegear.lexer.token.Token;

public abstract class SourceFile implements Comparable<SourceFile> {

	final public String path;
	final private List<Statement> statements;
	private int loc;

	protected SourceFile(final String path) {
		this.path = path;
		this.statements = new ArrayList<>();
		this.loc = 0;
	}

	@Override
	public boolean equals(final Object o) {

		if (!(o instanceof SourceFile)) {
			return false;
		}

		final SourceFile target = (SourceFile) o;
		return this.path.equals(target.path)
				&& (this.getClass() == target.getClass());
	}

	@Override
	public int hashCode() {
		return this.path.hashCode();
	}

	@Override
	public int compareTo(final SourceFile target) {
		return this.path.compareTo(target.path);
	}

	public void addStatement(final Statement statement) {
		this.statements.add(statement);
	}

	public void addStatements(final List<Statement> statements) {
		this.statements.addAll(statements);
	}

	public boolean hasStatement() {
		return !this.statements.isEmpty();
	}

	public List<Statement> getStatements() {
		final List<Statement> statements = new ArrayList<>(this.statements);
		return statements;
	}

	public List<Token> getTokens() {
		final List<Token> tokens = new ArrayList<>();
		for (final Statement statement : this.statements) {
			tokens.addAll(statement.tokens);
		}
		return tokens;
	}

	public void setLOC(final int loc) {
		this.loc = loc;
	}

	public int getLOC() {
		return this.loc;
	}

	abstract public LANGUAGE getLanguage();
}
