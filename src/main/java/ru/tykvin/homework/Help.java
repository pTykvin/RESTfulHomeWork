package ru.tykvin.homework;

/**
 * Класс исключительно для удобства проверки работы.
 */
public class Help {
    public static final String text = "<style>body{ padding: 30 }</style>" +
            "Использование:<br>" +
            "<b>/:</b> получить эту страницу<br>" +
            "<b>/student [GET]:</b> список студентов<br>" +
            "<b>/student [POST]{\"first_name\":\"\",\"last_name\":\"\"}:</b> добавить студента<br>" +
            "<b>/student/{id} [GET]:</b> получить студента с {id}<br>" +
            "<b>/student/{id} [DELETE]:</b> удалить студента {id}<br>" +
            "<b>/group [GET]:</b> получить список групп со студентами<br>" +
            "<b>/group [POST]{\"number\":\"\"}:</b> добаить группу с номером {number}<br>" +
            "<b>/group/{id} [GET]:</b> получить группу {id}<br>" +
            "<b>/group/{id} [DELETE]:</b> удалить группу {id}<br>" +
            "<b>/group/{id}/student [GET]:</b> список студентов группы с id {id}<br>" +
            "<b>/group/{id}/student/{student_id} [PUT]:</b> переместить студента {student_id} в группу {id}<br>" +
            "<b>/group/{id}/student/{student_id} [DELETE]:</b> убрать студента {student_id} из группы {id}<br>";
}
