/*
 * LensKit, an open source recommender systems toolkit.
 * Copyright 2010-2014 Regents of the University of Minnesota and contributors
 * Work on LensKit has been funded by the National Science Foundation under
 * grants IIS 05-34939, 08-08692, 08-12148, and 10-17697.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc., 51
 * Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA.
 */
package org.grouplens.lenskit.eval.data;

import com.google.common.collect.Lists;
import org.grouplens.lenskit.data.event.Rating;
import org.grouplens.lenskit.data.pref.Preference;
import org.grouplens.lenskit.util.table.writer.CSVWriter;

import java.io.IOException;
import java.util.List;

/**
 * @author <a href="http://www.grouplens.org">GroupLens Research</a>
 */
class CSVRatingWriter implements RatingWriter {
    private final CSVWriter tableWriter;

    public CSVRatingWriter(CSVWriter tw) {
        tableWriter = tw;
    }

    @Override
    public void writeRating(Rating r) throws IOException {
        List<Object> row = Lists.newArrayListWithCapacity(4);
        row.add(r.getUserId());
        row.add(r.getItemId());
        Preference p = r.getPreference();
        if (p == null) {
            row.add(null);
        } else {
            row.add(p.getValue());
        }
        row.add(r.getTimestamp());
        tableWriter.writeRow(row);
    }

    @Override
    public void close() throws IOException {
        tableWriter.close();
    }
}
