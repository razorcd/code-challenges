## Describing the logic

To write mathematical equations we used 3 types of parenthesis: `(), [], {}` and these were always hierarchical like this: `{[()]}`, always in this order. And to go even further we would reuse the curly braces like this `{ { { [ ( ) ] } } }`. It's like [Matryoshka doll](https://en.wikipedia.org/wiki/Matryoshka_doll) from Russia. :)

An example would be: `{(4 + 1) * { 5 * [( 5 ^ 2 - 4 ^ 2 ) * 2 ]}} / 2`. (where `5^2` means 5 to the power of 2).

## Describing the challenge

We need an application that would take a mathematical equation as string for input and return a boolean response representing the validity of the parenthesis ordering.

Some valid equations for which the application would return `true`:
    - `5 * 5`
    - `(4 + 1) * 2`
    - `(4 + 1) * [(2 - 4) / (2 + 2)]`
    - `{(4 + 1) * { 5 * [( 5 ^ 2 - 4 ^ 2 ) * 2 ]}} / 2`

Some invalid equations for which the application would return `false`:
    - `(4 + 1) * 2)`
    - `[4 + 1] * 2`
    - `4 * ([2 - 4] / [2 + 2])`
    - `4 * {(2 - 4) / (2 + 2)}`
    - `([4 * [1 + 2] - 1])`
