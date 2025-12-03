package weidig.adrian.java_j2ee.dialect;

import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.identity.GetGeneratedKeysDelegate;
import org.hibernate.dialect.identity.IdentityColumnSupport;
import org.hibernate.id.PostInsertIdentityPersister;

import java.sql.Types;

public class SQLiteDialect extends Dialect {

    public SQLiteDialect() {
        super();
        registerColumnType(Types.BIT, "integer");
        registerColumnType(Types.BIGINT, "bigint");
        registerColumnType(Types.SMALLINT, "smallint");
        registerColumnType(Types.TINYINT, "tinyint");
        registerColumnType(Types.INTEGER, "integer");
        registerColumnType(Types.CHAR, "char");
        registerColumnType(Types.VARCHAR, "varchar");
        registerColumnType(Types.FLOAT, "float");
        registerColumnType(Types.DOUBLE, "double");
        registerColumnType(Types.DATE, "date");
        registerColumnType(Types.TIME, "time");
        registerColumnType(Types.TIMESTAMP, "timestamp");
        registerColumnType(Types.VARBINARY, "blob");
        registerColumnType(Types.NUMERIC, "numeric");
        registerColumnType(Types.CLOB, "clob");
        registerColumnType(Types.BLOB, "blob");
    }

    @Override
    public IdentityColumnSupport getIdentityColumnSupport() {
        return new IdentityColumnSupport() {
            @Override
            public boolean supportsIdentityColumns() {
                return true;
            }

            @Override
            public boolean supportsInsertSelectIdentity() {
                return false;
            }

            @Override
            public boolean hasDataTypeInIdentityColumn() {
                return false;
            }

            @Override
            public String getIdentitySelectString(String table, String column, int type) {
                return "select last_insert_rowid()";
            }

            @Override
            public String getIdentityColumnString(int sqlType) {
                return "integer primary key autoincrement";
            }

            @Override
            public String getIdentityInsertString() {
                return "null";
            }

            @Override
            public GetGeneratedKeysDelegate buildGetGeneratedKeysDelegate(PostInsertIdentityPersister postInsertIdentityPersister, Dialect dialect) {
                return null;
            }

            @Override
            public String appendIdentitySelectToInsert(String insertString) {
                return insertString;
            }

            @Override
            public String appendIdentitySelectToInsert(String identityColumnName, String insertString) {
                return insertString;
            }
        };
    }

    @Override
    public boolean supportsLimit() {
        return true;
    }

    @Override
    public String getLimitString(String sql, boolean hasOffset) {
        return hasOffset ? sql + " limit ? offset ?" : sql + " limit ?";
    }

    // Zusätzliche wichtige Methoden für SQLite
    @Override
    public boolean supportsCurrentTimestampSelection() {
        return true;
    }

    @Override
    public boolean isCurrentTimestampSelectStringCallable() {
        return false;
    }

    @Override
    public String getCurrentTimestampSelectString() {
        return "select current_timestamp";
    }

    @Override
    public boolean supportsUnionAll() {
        return true;
    }

    @Override
    public boolean hasAlterTable() {
        return false;
    }

    @Override
    public boolean dropConstraints() {
        return false;
    }
}
