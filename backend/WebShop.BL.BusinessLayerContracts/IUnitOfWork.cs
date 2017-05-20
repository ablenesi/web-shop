namespace WebShop.BL.BusinessLayerContracts
{
    public interface IUnitOfWork
    {
        IProductOperations ProductOperations { get; }

        ICategoryOperations CategoryOperations { get; }

        IOrderOperations OrderOperations { get; }

        void SaveChanges();
    }
}
