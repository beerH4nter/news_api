import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import "../styles/NewsDetailPage.css";

const API_URL = "http://localhost:8080";

const NewsDetailPage = () => {
    const { id } = useParams();
    const [news, setNews] = useState(null);

    useEffect(() => {
        fetch(`${API_URL}/news/${id}`)
            .then((res) => res.json())
            .then((data) => {
                setNews(data);
            });
    }, [id]);

    if (!news) return <p>Загрузка...</p>;

    return (
        <div>
            <h2>{news.title}</h2>
            <p>
                <strong>Дата:</strong> {news.date}
            </p>
            <p>
                <strong>Описание:</strong> {news.description}
            </p>
            <p>{news.text}</p>
            <div className="main-page_img-container">
                <img src={`${API_URL}/image/${news.imagePathList[0]}`} alt="news" style={{ maxWidth: "100%" }} />
            </div>
        </div>
    );
};

export default NewsDetailPage;
