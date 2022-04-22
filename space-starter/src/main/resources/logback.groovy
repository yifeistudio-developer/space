import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.classic.filter.LevelFilter
import java.nio.charset.Charset
import static ch.qos.logback.classic.Level.*

import static ch.qos.logback.core.spi.FilterReply.ACCEPT
import static ch.qos.logback.core.spi.FilterReply.DENY

def FILE_LOG_PATTERN = "%date [%level] [%thread] [%file : %line] %msg%n"
def CONSOLE_LOG_PATTERN = "%green(%date{yyyy-MM-dd HH:mm:ss}) %highlight(%-5level) %green([%thread]) %magenta(%logger{50}) %cyan(%msg%n)"
def LOG_DIR = System.getProperty("user.dir") + "/log"
def LEVEL_ROOT = INFO

appender("console", ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        pattern = "${CONSOLE_LOG_PATTERN}"
        charset = Charset.forName("UTF-8")
    }
}

appender("debug", RollingFileAppender) {

    file = "${LOG_DIR}/debug.log"

    rollingPolicy(TimeBasedRollingPolicy) {
        fileNamePattern = "${LOG_DIR}/%d{yyyy-MM-dd}(%i)-debug.log"
        maxHistory = 15
        timeBasedFileNamingAndTriggeringPolicy(SizeAndTimeBasedFNATP) {
            maxFileSize = "200MB"
        }
    }

    encoder(PatternLayoutEncoder) {
        pattern = "${FILE_LOG_PATTERN}"
        charset = Charset.forName("UTF-8")
    }

    filter(LevelFilter) {
        level = DEBUG
        onMatch = ACCEPT
        onMismatch = DENY
    }
}

appender("info", RollingFileAppender) {

    file = "${LOG_DIR}/info.log"

    rollingPolicy(TimeBasedRollingPolicy) {
        fileNamePattern = "${LOG_DIR}/%d{yyyy-MM-dd}(%i)-info.log"
        maxHistory = 15
        timeBasedFileNamingAndTriggeringPolicy(SizeAndTimeBasedFNATP) {
            maxFileSize = "200MB"
        }
    }

    encoder(PatternLayoutEncoder) {
        pattern = "${FILE_LOG_PATTERN}"
        charset = Charset.forName("UTF-8")
    }

    filter(LevelFilter) {
        level = INFO
        onMatch = ACCEPT
        onMismatch = DENY
    }
}

appender("error", RollingFileAppender) {
    file = "${LOG_DIR}/error.log"
    rollingPolicy(TimeBasedRollingPolicy) {
        fileNamePattern = "${LOG_DIR}/%d{yyyy-MM-dd}(%i)-error.log"
        maxHistory = 30
        timeBasedFileNamingAndTriggeringPolicy(SizeAndTimeBasedFNATP) {
            maxFileSize = "200MB"
        }
    }
    encoder(PatternLayoutEncoder) {
        pattern = "${FILE_LOG_PATTERN}"
        charset = Charset.forName("UTF-8")
    }
    filter(LevelFilter) {
        level = ERROR
        onMatch = ACCEPT
        onMismatch = DENY
    }
}

root(LEVEL_ROOT, ["console", "debug", "info", "error"])
