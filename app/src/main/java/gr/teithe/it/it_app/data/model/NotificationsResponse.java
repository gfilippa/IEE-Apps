/*
 * Copyright (C) 2018-2020 Raf
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.

 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package gr.teithe.it.it_app.data.model;

import androidx.annotation.NonNull;

import java.util.List;

public class NotificationsResponse
{
    @NonNull
    private List<Notification> notifications;

    public NotificationsResponse(@NonNull List<Notification> notifications)
    {
        this.notifications = notifications;
    }

    @NonNull
    public List<Notification> getNotifications()
    {
        return notifications;
    }
}
