/*
 * Copyright 2013 National Bank of Belgium
 *
 * Licensed under the EUPL, Version 1.1 or – as soon they will be approved 
 * by the European Commission - subsequent versions of the EUPL (the "Licence");
 * You may not use this work except in compliance with the Licence.
 * You may obtain a copy of the Licence at:
 *
 * http://ec.europa.eu/idabc/eupl
 *
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the Licence is distributed on an "AS IS" basis,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the Licence for the specific language governing permissions and 
 * limitations under the Licence.
 */
package ec.tss.tsproviders;

import javax.annotation.Nonnull;

/**
 * Entry point for assertions of different data types. Each method in this class
 * is a static factory for the type-specific assertion objects.
 *
 * @author Philippe Charles
 * @since 2.2.0
 */
public class Assertions {

    /**
     * Creates a new instance of <code>{@link DataSetAssert}</code>.
     *
     * @param actual the actual value.
     * @return the created assertion object.
     */
    @Nonnull
    public static DataSetAssert assertThat(@Nonnull DataSet actual) {
        return new DataSetAssert(actual);
    }

    /**
     * Creates a new instance of <code>{@link DataSourceAssert}</code>.
     *
     * @param actual the actual value.
     * @return the created assertion object.
     */
    @Nonnull
    public static DataSourceAssert assertThat(@Nonnull DataSource actual) {
        return new DataSourceAssert(actual);
    }

    /**
     * Creates a new instance of <code>{@link IDataSourceLoaderAssert}</code>.
     *
     * @param actual the actual value.
     * @return the created assertion object.
     */
    @Nonnull
    public static IDataSourceLoaderAssert assertThat(@Nonnull IDataSourceLoader actual) {
        return new IDataSourceLoaderAssert(actual);
    }

    /**
     * Creates a new instance of <code>{@link IDataSourceProviderAssert}</code>.
     *
     * @param actual the actual value.
     * @return the created assertion object.
     */
    @Nonnull
    public static IDataSourceProviderAssert assertThat(@Nonnull IDataSourceProvider actual) {
        return new IDataSourceProviderAssert(actual);
    }

    /**
     * Creates a new instance of <code>{@link IFileBeanAssert}</code>.
     *
     * @param actual the actual value.
     * @return the created assertion object.
     */
    @Nonnull
    public static IFileBeanAssert assertThat(@Nonnull IFileBean actual) {
        return new IFileBeanAssert(actual);
    }

    /**
     * Creates a new instance of <code>{@link IFileLoaderAssert}</code>.
     *
     * @param actual the actual value.
     * @return the created assertion object.
     */
    @Nonnull
    public static IFileLoaderAssert assertThat(@Nonnull IFileLoader actual) {
        return new IFileLoaderAssert(actual);
    }

    /**
     * Creates a new <code>{@link Assertions}</code>.
     */
    protected Assertions() {
        // empty
    }
}
