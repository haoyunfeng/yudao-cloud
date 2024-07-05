package cn.iocoder.engine.drools.parser;


import cn.iocoder.engine.drools.vo.scorecard.ScoreCardStrategy;

/**
 * @author haoyunfeng
 * @create 2024/7/5 16:02
 * @description
 */
public abstract class AbstractParser {
    public abstract String parse(ScoreCardStrategy strategy);
}
