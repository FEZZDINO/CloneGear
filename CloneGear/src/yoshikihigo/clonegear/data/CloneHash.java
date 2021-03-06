package yoshikihigo.clonegear.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import yoshikihigo.clonegear.lexer.token.Token;

public class CloneHash {

	static public List<Token> getTokens(final CloneHash hash) {
		final List<Token> tokens = new ArrayList<>();
		for (final MD5 md5 : hash.value) {
			final List<Token> t = MD5.getTokens(md5);
			tokens.addAll(t);
		}
		return tokens;
	}

	final public MD5[] value;

	public CloneHash(final MD5[] value) {
		this.value = Arrays.copyOf(value, value.length);
	}

	@Override
	public int hashCode() {
		return Arrays.asList(this.value).stream().mapToInt(md5 -> md5.value[0])
				.sum();
	}

	@Override
	public boolean equals(final Object o) {
		if (!(o instanceof CloneHash)) {
			return false;
		}

		final CloneHash target = (CloneHash) o;
		if (this.value.length != target.value.length) {
			return false;
		}

		for (int index = 0; index < this.value.length; index++) {
			if (!Arrays.equals(this.value[index].value,
					target.value[index].value)) {
				return false;
			}
		}

		return true;
	}
}
