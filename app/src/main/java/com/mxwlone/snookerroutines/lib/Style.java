package com.mxwlone.snookerroutines.lib;

/**
 * DISCRETE:   Fixed number of attempts (e.g. 10). Count how many attempts are successful.
 *             Each success, e.g. a potted ball counts 1 score. The result is the number of
 *             successful attempts.
 *
 * CONTINUOUS: Count the ball's real score (black = 7, pink = 6, ..., red = 1) for each potted ball.
 *             Add the score until the first failed attempt. The result is the sum of the score.
 */
public enum Style {
    DISCRETE,
    CONTINUOUS
}
