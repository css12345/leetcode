package io.github.css12345.day17;

import static org.junit.Assert.assertArrayEquals;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 5441.保证文件名唯一<br>
 * https://leetcode-cn.com/contest/weekly-contest-194/problems/making-file-names-unique/
 * @author cs
 *
 */
public class GetFolderNames {

	public static void main(String[] args) {
		assertArrayEquals(new String[] { "gta", "gta(1)", "gta(2)", "avalon" },
				new GetFolderNames().getFolderNames(new String[] { "gta", "gta(1)", "gta", "avalon" }));
		assertArrayEquals(new String[] { "kaido", "kaido(1)", "kaido(2)", "kaido(1)(1)" },
				new GetFolderNames().getFolderNames(new String[] { "kaido", "kaido(1)", "kaido", "kaido(1)" }));
		assertArrayEquals(new String[] { "kaido", "kaido(5)", "kaido(1)", "kaido(1)(1)" },
				new GetFolderNames().getFolderNames(new String[] { "kaido", "kaido(5)", "kaido", "kaido(1)" }));
		assertArrayEquals(new String[] { "kingston(0)", "kingston", "kingston(1)" },
				new GetFolderNames().getFolderNames(new String[] { "kingston(0)", "kingston", "kingston(1)" }));
		assertArrayEquals(
				new String[] { "b(4)(3)", "d(1)", "k", "z(1)(4)", "u", "s(1)(2)", "q(1)(4)", "z(1)", "r", "b(3)(4)",
						"x", "e", "r(1)", "t", "e(1)", "z(2)", "d", "n(1)", "o", "o(1)", "t(1)", "l", "p", "a", "w",
						"j(3)", "w(1)", "c(3)", "q", "v", "u(1)" },
				new GetFolderNames().getFolderNames(new String[] { "b(4)(3)", "d(1)", "k", "z(1)(4)", "u", "s(1)(2)",
						"q(1)(4)", "z(1)", "r", "b(3)(4)", "x", "e", "r(1)", "t", "e", "z(2)", "d", "n(1)", "o", "o",
						"t(1)", "l", "p", "a", "w", "j(3)", "w", "c(3)", "q", "v", "u" }));
		assertArrayEquals(
				new String[] { "t", "t(1)", "t(4)(4)", "t(2)(3)", "t(2)", "t(3)", "t(4)(2)", "t(4)", "t(5)", "t(2)(2)",
						"t(6)", "t(7)", "t(8)", "t(9)", "t(2)(4)", "t(10)" },
				new GetFolderNames().getFolderNames(new String[] { "t", "t", "t(4)(4)", "t(2)(3)", "t(2)", "t",
						"t(4)(2)", "t", "t", "t(2)(2)", "t", "t", "t", "t", "t(2)(4)", "t" }));
		assertArrayEquals(new String[] { "l", "p(4)(4)", "v", "c", "i", "k(2)", "y", "a(4)(2)", "t", "a", "e",
				"e(1)(4)", "p", "p(1)", "s", "z", "s(1)", "q(2)(1)", "r(1)", "m", "b(3)", "u(2)", "j", "p(2)", "t(1)",
				"s(2)", "g", "o", "o(1)", "b(3)(3)", "k", "w(2)(3)", "q", "a(1)", "z(1)", "t(4)(4)", "s(2)(1)", "c(1)",
				"w", "u", "h", "g(1)", "b", "r", "c(1)(1)", "v(1)", "n(1)", "r(2)(1)", "u(1)", "t(2)(3)", "p(3)",
				"m(3)(1)", "h(1)", "o(2)", "y(1)", "n", "s(3)", "z(2)", "k(2)(1)", "x(4)", "l(1)(4)", "g(2)", "u(3)",
				"t(2)", "m(1)", "c(2)", "t(3)", "g(3)", "c(3)", "a(2)", "r(2)", "d", "y(2)", "b(1)", "p(4)", "m(2)",
				"w(2)(4)", "v(2)", "t(4)(2)", "x", "e(4)(1)", "h(2)", "f", "z(3)", "e(1)(4)(1)", "t(4)", "b(1)(1)",
				"x(4)(1)", "m(3)", "j(2)(4)", "s(3)(2)", "z(4)", "l(1)", "p(2)(2)", "g(3)(2)", "q(1)(4)", "h(1)(1)",
				"h(3)", "o(3)", "h(4)", "f(1)", "n(4)", "s(4)", "g(4)", "s(5)", "r(3)", "n(1)(1)", "x(3)", "r(4)",
				"f(3)(1)", "e(1)", "j(1)", "s(6)", "g(5)", "d(1)", "l(2)", "g(6)", "o(4)(3)", "x(4)(2)", "u(2)(4)",
				"x(1)", "t(5)", "f(2)", "j(1)(1)", "v(2)(1)", "w(1)", "v(3)", "t(2)(2)", "l(3)", "o(1)(1)", "a(3)",
				"y(3)", "q(4)", "m(1)(2)", "i(1)", "u(4)", "l(4)", "c(1)(2)", "g(7)", "l(2)(1)", "p(1)(1)", "k(1)",
				"d(2)", "l(5)", "o(4)", "m(4)", "i(1)(1)", "j(2)", "i(2)", "f(3)", "y(4)", "b(2)", "k(3)", "z(5)",
				"n(2)", "t(6)", "c(2)(1)", "y(5)", "q(1)", "b(4)", "t(7)", "m(5)", "g(1)(1)", "r(5)", "m(6)", "l(6)",
				"s(7)", "n(3)", "j(4)(4)", "m(3)(3)", "n(5)", "n(2)(3)", "s(8)", "t(8)", "l(7)", "e(2)", "p(5)", "q(2)",
				"v(4)", "v(5)", "b(5)", "w(2)", "m(7)", "p(6)", "g(8)", "h(2)(1)", "n(1)(2)", "q(3)", "x(4)(3)",
				"q(2)(4)", "s(2)(2)", "w(3)", "k(4)", "f(4)", "v(6)", "n(6)", "q(5)", "w(4)", "y(3)(1)", "a(4)(1)",
				"r(1)(1)", "r(6)", "f(4)(2)", "l(8)", "f(2)(3)", "p(7)", "o(5)", "h(5)", "t(9)", "i(3)", "r(7)", "k(5)",
				"p(8)", "l(9)", "o(3)(4)", "f(1)(1)", "j(4)", "f(3)(4)", "o(2)(1)", "j(3)", "k(2)(2)", "k(6)",
				"x(4)(4)", "s(3)(4)", "p(1)(3)", "y(6)", "r(2)(2)", "i(3)(4)", "j(5)", "p(4)(3)", "e(3)", "j(4)(1)",
				"g(4)(2)", "x(2)", "l(10)", "x(5)", "g(9)", "w(5)", "o(6)", "t(2)(4)", "s(9)", "f(5)", "o(7)", "h(6)",
				"h(2)(4)", "z(4)(2)", "w(6)", "l(11)", "n(7)", "q(6)", "w(7)", "l(12)", "a(4)", "o(8)", "v(7)", "h(7)",
				"b(6)", "v(8)", "b(7)", "k(7)", "x(2)(1)", "r(2)(1)(1)", "g(2)(1)", "c(4)(3)", "w(1)(1)", "g(4)(1)",
				"z(6)", "i(3)(2)", "r(8)", "e(4)", "p(9)", "z(4)(2)(1)", "s(10)", "c(1)(3)", "h(8)", "l(13)", "j(3)(4)",
				"x(1)(2)", "a(5)", "b(8)", "t(10)", "c(4)" },
				new GetFolderNames().getFolderNames(new String[] { "l", "p(4)(4)", "v", "c", "i", "k(2)", "y",
						"a(4)(2)", "t", "a", "e", "e(1)(4)", "p", "p", "s", "z", "s", "q(2)(1)", "r(1)", "m", "b(3)",
						"u(2)", "j", "p", "t", "s", "g", "o", "o", "b(3)(3)", "k", "w(2)(3)", "q", "a", "z", "t(4)(4)",
						"s(2)", "c", "w", "u", "h", "g", "b", "r", "c(1)", "v", "n(1)", "r(2)(1)", "u", "t(2)(3)", "p",
						"m(3)(1)", "h", "o", "y", "n", "s", "z", "k(2)", "x(4)", "l(1)(4)", "g(2)", "u", "t(2)", "m",
						"c", "t", "g", "c", "a(2)", "r", "d", "y", "b", "p", "m", "w(2)(4)", "v", "t(4)(2)", "x",
						"e(4)(1)", "h", "f", "z", "e(1)(4)", "t", "b(1)", "x(4)", "m(3)", "j(2)(4)", "s(3)(2)", "z",
						"l", "p(2)(2)", "g(3)(2)", "q(1)(4)", "h(1)(1)", "h", "o(3)", "h", "f", "n(4)", "s(4)", "g",
						"s", "r", "n(1)(1)", "x(3)", "r", "f(3)(1)", "e", "j", "s", "g", "d", "l", "g", "o(4)(3)",
						"x(4)", "u(2)(4)", "x", "t", "f", "j(1)", "v(2)", "w", "v", "t(2)(2)", "l", "o(1)(1)", "a", "y",
						"q(4)", "m(1)(2)", "i", "u", "l", "c(1)", "g", "l(2)", "p(1)(1)", "k", "d", "l", "o", "m",
						"i(1)", "j", "i", "f", "y", "b", "k", "z", "n", "t", "c(2)", "y", "q", "b", "t", "m", "g(1)",
						"r", "m", "l", "s", "n", "j(4)(4)", "m(3)(3)", "n", "n(2)(3)", "s", "t", "l", "e", "p", "q(2)",
						"v", "v", "b", "w", "m", "p", "g", "h(2)", "n(1)", "q", "x(4)", "q(2)(4)", "s(2)", "w", "k",
						"f", "v", "n", "q", "w", "y(3)", "a(4)(1)", "r(1)", "r", "f(4)(2)", "l", "f(2)(3)", "p", "o",
						"h", "t", "i", "r", "k", "p", "l", "o(3)(4)", "f(1)", "j(4)", "f(3)(4)", "o(2)(1)", "j", "k(2)",
						"k", "x(4)", "s(3)(4)", "p(1)(3)", "y", "r(2)", "i(3)(4)", "j", "p(4)(3)", "e", "j(4)",
						"g(4)(2)", "x", "l", "x", "g", "w", "o", "t(2)(4)", "s", "f", "o", "h", "h(2)(4)", "z(4)(2)",
						"w", "l", "n", "q", "w", "l", "a", "o", "v", "h", "b", "v", "b", "k", "x(2)", "r(2)(1)", "g(2)",
						"c(4)(3)", "w(1)", "g(4)", "z", "i(3)(2)", "r", "e", "p", "z(4)(2)", "s", "c(1)", "h", "l",
						"j(3)(4)", "x(1)(2)", "a", "b", "t", "c" }));
	}

	static class NameIndexContainer {
		private Set<Integer> allocatedIndexs = new HashSet<>();
		private int minIndex = 0;

		public int getMinIndex() {
			while (allocatedIndexs.contains(minIndex))
				minIndex++;
			allocatedIndexs.add(minIndex);
			return minIndex;
		}

		public void addIndex(int index) {
			allocatedIndexs.add(index);
		}

		@Override
		public String toString() {
			return "AllocatedIndexs [allocatedIndexs=" + allocatedIndexs + ", minIndex=" + minIndex + "]";
		}

	}

	public String[] getFolderNames(String[] names) {
		String[] ans = new String[names.length];
		Map<String, NameIndexContainer> namesMap = new HashMap<>();
		for (int i = 0; i < names.length; i++) {
			String name = names[i];
			// 判断名字是否含括号，如果最后的括号内容是0，认为不含；
			boolean containBracket = true;
			int lastIndexOfLeftBracket = name.lastIndexOf('(');
			if (lastIndexOfLeftBracket == -1)
				containBracket = false;
			else {
				int contentOfLastBracket = Integer
						.parseInt(name.substring(lastIndexOfLeftBracket + 1, name.lastIndexOf(')')));
				if (contentOfLastBracket == 0)
					containBracket = false;
			}

			// 如果不含括号，检查map是否包含名字，如果不包含，添加；包含，添加后缀
			if (!containBracket) {
				if (!namesMap.containsKey(name) || (!namesMap.get(name).allocatedIndexs.contains(0))) {
					ans[i] = name;
					directUse(namesMap, name);
				} else {
					addNewSuffix(ans, namesMap, i, name);
				}
			} else {
				// 检查带括号是否被占用，如果没有，直接使用；被占用，添加后缀
				if (!namesMap.containsKey(name) || (!namesMap.get(name).allocatedIndexs.contains(0))) {
					ans[i] = name;
					directUse(namesMap, name);

					// 添加去掉括号后的情况
					int contentOfLastBracket = Integer
							.parseInt(name.substring(lastIndexOfLeftBracket + 1, name.lastIndexOf(')')));
					String replaceLastBracketName = name.substring(0, lastIndexOfLeftBracket);
					NameIndexContainer replaceLastBracketContainer = namesMap.getOrDefault(replaceLastBracketName,
							new NameIndexContainer());
					replaceLastBracketContainer.addIndex(contentOfLastBracket);
					if (!namesMap.containsKey(replaceLastBracketName))
						namesMap.put(replaceLastBracketName, replaceLastBracketContainer);
				} else {
					addNewSuffix(ans, namesMap, i, name);
				}
			}
		}
		return ans;
	}

	private void directUse(Map<String, NameIndexContainer> namesMap, String name) {
		if (!namesMap.containsKey(name)) {
			NameIndexContainer container = new NameIndexContainer();
			container.addIndex(0);
			namesMap.put(name, container);
		} else {
			namesMap.get(name).addIndex(0);
		}
	}

	private void addNewSuffix(String[] ans, Map<String, NameIndexContainer> namesMap, int i, String name) {
		NameIndexContainer container = namesMap.get(name);
		int suffix = container.getMinIndex();

		ans[i] = name + "(" + suffix + ")";
		NameIndexContainer suffixContainer = namesMap.getOrDefault(ans[i], new NameIndexContainer());
		suffixContainer.addIndex(0);
		namesMap.put(ans[i], suffixContainer);
	}
}
